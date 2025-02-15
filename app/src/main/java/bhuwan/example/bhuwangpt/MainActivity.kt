package bhuwan.example.bhuwangpt

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import bhuwan.example.bhuwangpt.Models.ChatClass
import bhuwan.example.bhuwangpt.Models.GptRequest
import bhuwan.example.bhuwangpt.RecyclerView.Adapter
import bhuwan.example.bhuwangpt.ViewModel.ChatViewModel
import bhuwan.example.bhuwangpt.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var adapter: Adapter
    private var myRecyclerViewList = mutableListOf<ChatClass>()
    private lateinit var viewModel: ChatViewModel

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this)[ChatViewModel::class.java]

        setUpRecyclerView()
        observeData()

        binding.imageButton.setOnClickListener {
            val value = binding.editTextText.text.toString()
            if (value.isNotEmpty()) {
                myRecyclerViewList.add(ChatClass(value, false))
                adapter.notifyDataSetChanged()
                binding.recyclerView.scrollToPosition(myRecyclerViewList.size - 1)
                binding.editTextText.text.clear()
                viewModel.fetchChatGptData(GptRequest(value))
            }
        }
    }

    private fun setUpRecyclerView() {
        adapter = Adapter(myRecyclerViewList, this)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
    }

    private fun observeData() {
        viewModel.data.observe(this, Observer { chatResponse ->
            chatResponse?.let {
                val responseText = it[0].generated_text
                myRecyclerViewList.add(ChatClass(responseText, true))
                adapter.notifyDataSetChanged()
                binding.recyclerView.scrollToPosition(myRecyclerViewList.size - 1)
            }
        })

        viewModel.isLoading.observe(this, Observer { isLoading ->
            binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        })
    }
}