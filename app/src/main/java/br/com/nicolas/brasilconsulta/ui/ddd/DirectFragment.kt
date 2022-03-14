package br.com.nicolas.brasilconsulta.ui.ddd

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.nicolas.brasilconsulta.R

class DirectFragment : Fragment() {

    private lateinit var viewModel: DirectViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.direct_fragment, container, false)
    }
}