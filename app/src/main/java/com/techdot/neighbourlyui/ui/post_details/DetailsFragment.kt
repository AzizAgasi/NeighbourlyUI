package com.techdot.neighbourlyui.ui.post_details

import android.app.Dialog
import android.content.res.Configuration
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.core.widget.doOnTextChanged
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.techdot.neighbourlyui.R
import com.techdot.neighbourlyui.databinding.FragmentDetailsBinding
import com.techdot.neighbourlyui.model.Post


private const val POST = "post"

class DetailsFragment : BottomSheetDialogFragment() {
    // TODO: Rename and change types of parameters
    private var post: Post? = null
    private lateinit var binding: FragmentDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            post = it.getParcelable<Post>(POST)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_details, container, false)
        binding = FragmentDetailsBinding.inflate(LayoutInflater.from(context), container, false)

        binding.userName.text = post?.userName
        binding.userImage.setImageResource(post!!.userImage)
        binding.userLocation.text = post?.userLocation
        binding.userQuestion.text = post?.question


        binding.commenterName.text = post?.commenterName
        binding.commenterImage.setImageResource(post!!.commenterImage)
        binding.commenterReply.text = post?.answer

        binding.answerButton.setOnClickListener {
            addComment(binding)
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (view.parent as View).setBackgroundColor(Color.TRANSPARENT)
        val resources = resources

        if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
            val parent = view.parent as View
            val layoutParams = parent.layoutParams as CoordinatorLayout.LayoutParams
            layoutParams.setMargins(
                resources.getDimensionPixelSize(R.dimen.screen_margin), // LEFT 16dp
                0,
                resources.getDimensionPixelSize(R.dimen.screen_margin), // RIGHT 16dp
                0
            )
            parent.layoutParams = layoutParams
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val bottomSheetDialog = super.onCreateDialog(savedInstanceState) as BottomSheetDialog
        bottomSheetDialog.setOnShowListener {
            val dialog = it as BottomSheetDialog
            val bottomSheet = dialog.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet)
            BottomSheetBehavior.from(bottomSheet!!).state = BottomSheetBehavior.STATE_EXPANDED
        }
        return bottomSheetDialog
    }

    private fun addComment(binding: FragmentDetailsBinding) {
        binding.answerText.visibility = View.INVISIBLE

        binding.userAnswerImage.apply {
            visibility = View.VISIBLE
            setImageResource(post!!.userImage)
        }
        binding.answerField.apply {
            visibility = View.VISIBLE
            hint = "Enter an answer                "
        }

        binding.answerField.doOnTextChanged { text, start, before, count ->
            binding.userAnswerImage.visibility = View.GONE
            binding.sendButton.visibility = View.VISIBLE
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment DetailsFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(post: Post) =
            DetailsFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(POST, post)
                }
            }
    }
}