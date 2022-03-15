package com.example.rickandmorty.view.fragment

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.adapter.CharacterAdapter
import com.example.rickandmorty.R
import com.example.rickandmorty.databinding.FragmentListBinding
import com.example.rickandmorty.viewmodel.CharacterViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ListFragment : Fragment() {


    lateinit var binding: FragmentListBinding

    private val sharedPrefere: SharedPreferences by lazy {
        requireActivity().getSharedPreferences("aliveKey", Context.MODE_PRIVATE)

    }

    private val adapterCharacter: CharacterAdapter by lazy {
        CharacterAdapter()
    }
    private val viewModel: CharacterViewModel by viewModels()


    //viewModel ekle

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_list, container, false)
        return binding.root

        binding.lifecycleOwner = viewLifecycleOwner

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        getRv()
        loadData()

        adapterCharacter.setResulsClickListener {

            findNavController().navigate(
                ListFragmentDirections.actionListFragmentToDetailFragment(
                    it
                )
            )
        }

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.detail_menu, menu)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.alive -> {
                sharedPrefere.edit().apply {
                    putString("alive", "Alive")
                    apply()
                }
                viewModel.setQuery(sharedPrefere.getString("alive", "Alive"))
            }
            R.id.dead -> {
                sharedPrefere.edit().apply {
                    putString("alive", "Dead")
                    apply()
                }
                viewModel.setQuery("Dead")
            }
            R.id.unknow -> {
                sharedPrefere.edit().apply {
                    putString("alive", "Unknow")
                    apply()
                }
                viewModel.setQuery("Unknow")
            }
            else -> viewModel.setQuery("")


        }
        return super.onOptionsItemSelected(item)
    }


    fun getRv() {
        viewModel.setQuery(sharedPrefere.getString("alive", "Alive"))
        binding.rV.apply {
            adapter = adapterCharacter
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
        }


    }

    private fun loadData() {

        lifecycleScope.launch {
            viewModel.listData.observe(viewLifecycleOwner) {

                adapterCharacter.submitData(lifecycle, it)
            }

        }
    }
}










