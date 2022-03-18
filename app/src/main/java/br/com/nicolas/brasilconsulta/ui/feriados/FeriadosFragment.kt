package br.com.nicolas.brasilconsulta.ui.feriados

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import br.com.nicolas.brasilconsulta.R
import br.com.nicolas.brasilconsulta.common.GenericSimpleRecyclerBindingInterface
import br.com.nicolas.brasilconsulta.common.RecyclerAdapter
import br.com.nicolas.brasilconsulta.common.UiState
import br.com.nicolas.brasilconsulta.data.datasource.response.FeriadosResponse
import br.com.nicolas.brasilconsulta.data.datasource.response.FeriadosResponseItem
import br.com.nicolas.brasilconsulta.databinding.FeriadosFragmentBinding
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class FeriadosFragment : Fragment() {

    private val viewModel: FeriadosViewModel by viewModel()

    private var _binding: FeriadosFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FeriadosFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch {
            viewModel.getHolidays("2020")
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
    }

    private fun setupRecyclerView(feriadosResponse: FeriadosResponse) {
        val bindingInterface = RecyclerAdapter(
            feriadosResponse,
            R.layout.item_holiday,
            object : GenericSimpleRecyclerBindingInterface<FeriadosResponseItem> {
                override fun bindData(item: FeriadosResponseItem, view: View) {
                    val tvName = view.findViewById<TextView>(R.id.textView_name)
                    val tvType = view.findViewById<TextView>(R.id.textView_type)
                    val tvDate = view.findViewById<TextView>(R.id.textView_date)

                    tvName.text = item.name
                    tvType.text = item.type
                    tvDate.text = item.date
                }
            }
        )

        with(binding.recyclerFeriados) {
            setHasFixedSize(true)
            adapter = bindingInterface
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}