package com.example.fruitsapp.presentation.ui.fragments.addfruit

import android.app.AlertDialog
import android.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.fruitsapp.R
import com.example.fruitsapp.domain.model.NewFruitDto
import com.example.fruitsapp.presentation.presenters.addfruit.AddFruitPresenter
import com.example.fruitsapp.presentation.ui.dialogs.RequestErrorDialog
import kotlinx.android.synthetic.main.fragment_add_fruit.*
import javax.inject.Inject

class AddFruitFragment: Fragment(), AddFruitFragmentView {

    lateinit var dialog: AlertDialog

    @Inject
    lateinit var presenter: AddFruitPresenter

    fun setupComponent() {
        (activity as AddFruitFragmentInjector).inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupComponent()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_add_fruit, container, false)

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupUI()
        hideProgress()
    }

    private fun setupUI() {
        send_fruit.setOnClickListener { sendFruitData() }
    }

    private fun gatherFruitData(): NewFruitDto {
        val fruitName = name.text.toString()
        var fruitWeight: Long
        try {
            fruitWeight = weight.text.toString().toLong()
        }
        catch (e: NumberFormatException) {
            fruitWeight = 0
        }

        val fruitColor = color.text.toString()
        val fruitDeliciousnes = deliciousnes_switch.isChecked

        val newFruit = NewFruitDto(
                name = fruitName,
                weight = fruitWeight,
                color = fruitColor,
                delicious = fruitDeliciousnes
        )

        return newFruit
    }

    override fun sendFruitData() {
        gatherFruitData()
        presenter.sendNewFruit(gatherFruitData())
    }

    override fun showProgress() {
        progress_view.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        progress_view.visibility = View.GONE
    }

    override fun dataSendSuccessful() {
        Toast.makeText(activity,getString(R.string.fruit_sent_sucessfull),Toast.LENGTH_LONG).show()
    }

    override fun errorSendingData() {
        Toast.makeText(activity,getString(R.string.fruit_sent_error),Toast.LENGTH_LONG).show()
    }

    private fun createDialog(): AlertDialog {
        return RequestErrorDialog.newInstance(activity, {
            presenter.sendNewFruit(gatherFruitData())
        })
    }

    override fun showErrorDialog() {
        dialog = createDialog()
        dialog.show()
    }
}