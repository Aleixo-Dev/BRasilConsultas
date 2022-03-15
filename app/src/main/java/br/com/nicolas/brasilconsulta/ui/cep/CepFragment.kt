package br.com.nicolas.brasilconsulta.ui.cep

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import br.com.nicolas.brasilconsulta.common.UiState
import br.com.nicolas.brasilconsulta.databinding.CepFragmentBinding
import br.com.nicolas.brasilconsulta.utils.hideKeyboard
import org.koin.androidx.viewmodel.ext.android.viewModel

class CepFragment : Fragment() {

    private val viewModel: CepViewModel by viewModel()

    private var _binding: CepFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = CepFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupButton()
        observerChangeInViewModel()
    }

    private fun observerChangeInViewModel() {
        viewModel.uiState.observe(viewLifecycleOwner) {
            when (it) {
                is UiState.Loading -> {
                }
                is UiState.Success -> {
                    it.data.let { cep ->
                        setupTextViews(
                            cep.cep,
                            cep.state,
                            cep.city,
                            cep.neighborhood,
                            cep.street
                        )
                    }
                }
                is UiState.Error -> {
                    Toast.makeText(requireContext(), it.error, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun setupTextViews(
        cep: String,
        state: String,
        city: String,
        neighborhood: String,
        street: String,
    ) = binding.apply {
        tvCep.text = cep
        tvState.text = state
        tvCity.text = city
        tvNeighborhood.text = neighborhood
        tvStreet.text = street
    }

    private fun setupButton() = binding.apply {
        button.setOnClickListener {
            fetchCep(setupInput())
            it.hideKeyboard(it)
        }
    }

    private fun CepFragmentBinding.setupInput(): String {
        val result = inputCep.text.toString()
        inputCep.text?.clear()
        return result
    }

    private fun fetchCep(cep: String) {
        viewModel.getCep(cep)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}