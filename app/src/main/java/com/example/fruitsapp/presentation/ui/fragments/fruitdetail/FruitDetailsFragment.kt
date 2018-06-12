package com.example.fruitsapp.presentation.ui.fragments.fruitdetail

import android.app.AlertDialog
import android.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.fruitsapp.R
import com.example.fruitsapp.domain.model.FragmentArgument
import com.example.fruitsapp.domain.model.Fruit
import com.example.fruitsapp.presentation.presenters.fruitdetail.FruitDetailsPresenter
import com.example.fruitsapp.presentation.ui.dialogs.RequestErrorDialog
import kotlinx.android.synthetic.main.fragment_fruit_details.*
import javax.inject.Inject

class FruitDetailsFragment: Fragment(), FruitDetailsFragmentView {

    lateinit var dialog: AlertDialog

    @Inject
    lateinit var presenter: FruitDetailsPresenter

    companion object {

        val FRUIT_DETAIL_FRAGMENT_ID = "FRUIT_DETAIL_FRAGMENT_ID"

        class FruitDetailsFragmentArgument(val id: Long): FragmentArgument

        fun newInstance(argument: FragmentArgument?): Fragment {
            val arguments = Bundle()
            if (argument != null && argument is FruitDetailsFragmentArgument) {
                arguments.putLong(FRUIT_DETAIL_FRAGMENT_ID,argument.id)
            }
            val fragment = FruitDetailsFragment()
            fragment.arguments = arguments
            return fragment
        }
    }

    fun setupComponent() {
        (activity as FruitDetailsFragmentInjector).inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupComponent()
        presenter.setFruitId(arguments.getLong(FRUIT_DETAIL_FRAGMENT_ID))
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_fruit_details, container, false)

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        showProgress()
        hideContent()

        presenter.sendFruitDetailsRequest()
    }

    override fun setFruitDetails(data: Fruit) {
        name.text = data.name
        weight.text = data.weight.toString()
        deliciousnes.isChecked = data.delicious
        color.text = data.color
        created_date.text = data.created_at
        updated_date.text = data.updated_at
    }

    override fun showProgress() {
        progress_view.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        progress_view.visibility = View.GONE
    }

    override fun showContent() {
        content_layout.visibility = View.VISIBLE
    }

    override fun hideContent() {
        content_layout.visibility = View.GONE
    }

    private fun createDialog(): AlertDialog {
        return RequestErrorDialog.newInstance(activity, {presenter.sendFruitDetailsRequest()})
    }

    override fun showErrorDialog() {
        dialog = createDialog()
        dialog.show()
    }

}