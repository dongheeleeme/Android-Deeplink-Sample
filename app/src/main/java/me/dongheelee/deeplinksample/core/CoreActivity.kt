package me.dongheelee.deeplinksample.core

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity

abstract class CoreActivity : AppCompatActivity() {

    @LayoutRes
    abstract fun getLayoutResId(): Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutResId())
        setSavedInstanceState(savedInstanceState)
    }

    protected fun getBundle(savedInstanceState: Bundle?): Bundle? =
        savedInstanceState ?: intent.extras

    protected open fun setSavedInstanceState(savedInstanceState: Bundle?) {
        // no-op
    }
}
