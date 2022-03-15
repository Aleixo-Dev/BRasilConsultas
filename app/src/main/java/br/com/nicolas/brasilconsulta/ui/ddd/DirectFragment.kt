package br.com.nicolas.brasilconsulta.ui.ddd

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import br.com.nicolas.brasilconsulta.R
import br.com.nicolas.brasilconsulta.common.GenericSimpleRecyclerBindingInterface
import br.com.nicolas.brasilconsulta.common.RecyclerAdapter
import br.com.nicolas.brasilconsulta.common.UiState
import br.com.nicolas.brasilconsulta.databinding.DirectFragmentBinding
import br.com.nicolas.brasilconsulta.utils.hideKeyboard
import org.koin.androidx.viewmodel.ext.android.viewModel

class DirectFragment : Fragment() {

    private var _binding: DirectFragmentBinding? = null
    private val binding get() = _binding!!

    private val viewModel: DirectViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DirectFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupButton()
        observeChangesInViewModel()
    }

    private fun observeChangesInViewModel() {
        viewModel.uiState.observe(viewLifecycleOwner) { state ->
            when (state) {
                is UiState.Loading -> {

                }
                is UiState.Success -> {
                    setupRecyclerView(state.data)
                }
                is UiState.Error -> {

                }
            }
        }
    }

    private fun setupRecyclerView(directList: List<String>) {
        val bindingInterface = RecyclerAdapter(
            directList,
            R.layout.item_ddd,
            object : GenericSimpleRecyclerBindingInterface<String> {
                @SuppressLint("SetTextI18n")
                override fun bindData(item: String, view: View) {
                    val tvDDD: TextView = view.findViewById(R.id.tv_ddd)
                    tvDDD.text = item
                }
            }
        )
        with(binding.layoutDdd) {
            setHasFixedSize(true)
            adapter = bindingInterface
        }
    }

    private fun DirectFragmentBinding.setupInputText(): String {
        val result = inputDdd.text.toString()
        inputDdd.text?.clear()
        return result
    }

    private fun fetchDirect(directCode: String) {
        viewModel.fetchDirect(directCode)
    }

    private fun setupButton() = binding.apply {
        buttonDirect.setOnClickListener {
            fetchDirect(setupInputText())
            it.hideKeyboard(it)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}