package com.example.roborazziexamplewithpts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.roborazziexamplewithpts.databinding.FragmentFirstBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

  private var _binding: FragmentFirstBinding? = null

  // This property is only valid between onCreateView and
  // onDestroyView.
  private val binding get() = _binding!!

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {

    _binding = FragmentFirstBinding.inflate(inflater, container, false)
    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    binding.compose.setContent {
      SampleComposableFunction()
    }

    binding.buttonFirst.setOnClickListener {
      findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
    }
  }

  override fun onDestroyView() {
    super.onDestroyView()
    _binding = null
  }
}

@Composable
fun SampleComposableFunction() {
  var count by remember { mutableStateOf(2) }
  Column(
    Modifier
      .testTag("MyColumn")
  ) {
    Text(
      text = "Add",
      Modifier
        .testTag("AddBoxButton")
        .background(Color.Gray)
        .clickable {
          count++
        }
    )
    Text(
      text = "Sub",
      Modifier
        .testTag("SubBoxButton")
        .background(Color.Gray)
        .clickable {
          count--
        }
    )
    (0 until count).forEach { index ->
      Box(
        Modifier
          .background(Color.Red)
          .testTag("child:$index")
          .size(25.dp)
      ) {
        Text(
          text = "$index/$count"
        )
      }
    }
  }
}
