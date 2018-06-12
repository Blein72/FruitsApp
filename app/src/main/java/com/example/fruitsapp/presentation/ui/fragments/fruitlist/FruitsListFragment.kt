package com.example.fruitsapp.presentation.ui.fragments.fruitlist

import android.app.AlertDialog
import android.app.Fragment
import android.databinding.ObservableArrayList
import android.os.Bundle
import android.view.*
import com.example.fruitsapp.BR
import com.example.fruitsapp.R
import com.example.fruitsapp.databinding.ListItemFruitBinding
import com.example.fruitsapp.domain.model.Fruit
import com.example.fruitsapp.presentation.presenters.fruitslist.FruitsListPresenter
import com.example.fruitsapp.presentation.ui.activities.main.MainScreen
import com.example.fruitsapp.presentation.ui.activities.main.MainScreenTypesRouter
import com.example.fruitsapp.presentation.ui.dialogs.RequestErrorDialog
import com.example.fruitsapp.presentation.ui.fragments.fruitdetail.FruitDetailsFragment
import com.github.nitrico.lastadapter.LastAdapter
import kotlinx.android.synthetic.main.fragment_fruits_list.*
import javax.inject.Inject

class FruitsListFragment: Fragment(), FruitsListFragmentView {

    lateinit var dialog: AlertDialog

    @Inject
    lateinit var presenter: FruitsListPresenter

    private val fruitsDataList: ObservableArrayList<Fruit> = ObservableArrayList()
    private val fruitItem:Int = BR.fruitItem
    private val adapter: LastAdapter = LastAdapter(list = fruitsDataList, variable = fruitItem, stableIds = true)
            .map<Fruit, ListItemFruitBinding>(R.layout.list_item_fruit) {
                onClick { presenter.goToFruitDetails(it.binding.fruitItem?.id!!) }
            }

    private fun setupComponent() {
        (activity as FruitsListFragmentInjector).inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance=true

        setHasOptionsMenu(true)

        setupComponent()
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        //super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.fragment_list_menu, menu);
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        when(item?.itemId) {
            R.id.repeat_request -> presenter.sendFruitListRequest()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_fruits_list, container, false)

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter.into(fruitsList)
        add_fruit.setOnClickListener { presenter.goToAddFruit() }

        showProgress()
        hideContent()

        presenter.getCachedListAndSendRequest()
    }

    override fun setFruitListData(data: List<Fruit>) {
        fruitsDataList.clear()
        fruitsDataList.addAll(data)
    }

    private fun createDialog(): AlertDialog {
       return RequestErrorDialog.newInstance(activity, {presenter.getFruitslist()})
    }

    override fun showErrorDialog() {
        dialog = createDialog()
        dialog.show()
    }

    override fun showProgress() {
        progressView.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        progressView.visibility = View.GONE
    }

    override fun showContent() {
        fruitsList.visibility = View.VISIBLE
    }

    override fun hideContent() {
        fruitsList.visibility = View.GONE
    }

    override fun showFruitDetails(id: Long) {
        (activity as MainScreen).setNextFragment(
                data=FruitDetailsFragment.Companion.FruitDetailsFragmentArgument(id),
                type = MainScreenTypesRouter.Companion.Types.FRUIT_DETAILS_SCREEN)
    }

    override fun showAddFruitFragment() {
        (activity as MainScreen).setNextFragment(
                type = MainScreenTypesRouter.Companion.Types.ADD_FRUIT_SCREEN)
    }
}