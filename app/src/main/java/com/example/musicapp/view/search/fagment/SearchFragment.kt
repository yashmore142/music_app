package com.example.musicapp.view.search.fagment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.musicapp.databinding.FragmentSearchBinding
import com.example.musicapp.view.search.adapter.SearchAdapter
import com.example.musicapp.view.search.model.Item
import com.example.musicapp.view.search.viewmodel.SearchViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : Fragment() {
    private lateinit var mBinding: FragmentSearchBinding
    private val viewModel by viewModels<SearchViewModel>()
    private lateinit var searchAdapter: SearchAdapter
    private val list : ArrayList<Item> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = FragmentSearchBinding.inflate(layoutInflater)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mBinding.edtSearch.doAfterTextChanged { searchString ->
            if (searchString!!.length>= 3) {
                viewModel.getSearch(searchString.toString(), "track")
            }
        }

        observer()
        setUpAdapter()
    }
    private fun setUpAdapter() {
        searchAdapter = SearchAdapter(requireActivity())
        mBinding.rvSearch.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        mBinding.rvSearch.adapter = searchAdapter
    }
    private fun observer() {
        viewModel.searchResponse.observe(viewLifecycleOwner) {
            if (it.tracks != null) {
                list.clear()
                it.tracks!!.items?.let { it1 -> list.addAll(it1) }
                searchAdapter.updateList(list)
                Toast.makeText(requireActivity(), "done bro", Toast.LENGTH_SHORT).show()

            }
        }
    }

}