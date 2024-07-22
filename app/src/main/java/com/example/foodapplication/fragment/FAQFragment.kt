package com.example.foodapplication.fragment

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ListView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.foodapplication.R


class FAQFragment : Fragment() {


    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_faq, container, false)
        val faqListView: ListView = view.findViewById(R.id.faq_list_view)

        val questions = resources.getStringArray(R.array.faq_questions)
        val answers = resources.getStringArray(R.array.faq_answers)

        val faqItems = questions.indices.map { i -> questions[i] to answers[i] }

        val adapter = FaqAdapter(requireContext(), faqItems)
        faqListView.adapter = adapter

        return view
    }

    private class FaqAdapter(
        private val context: Context,
        private val faqItems: List<Pair<String, String>>
    ) : BaseAdapter() {

        override fun getCount(): Int = faqItems.size

        override fun getItem(position: Int): Pair<String, String> = faqItems[position]

        override fun getItemId(position: Int): Long = position.toLong()

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            val view = convertView ?: LayoutInflater.from(context)
                .inflate(R.layout.faq_item, parent, false)

            val questionTextView: TextView = view.findViewById(R.id.question)
            val answerTextView: TextView = view.findViewById(R.id.answer)

            val (question, answer) = getItem(position)

            questionTextView.text = question
            answerTextView.text = answer

            return view
        }

    }
}