package com.example.tabiyat.ui

import android.os.Bundle
import android.view.View
import android.widget.TextView
import java.util.regex.Pattern


 fun setVisibility(view: View, visible: Int) {
    view.visibility = visible
}

fun androidx.appcompat.widget.Toolbar.setTitle(
    label: CharSequence?,
    toolbarTxt: TextView,
    arguments: Bundle?
) {
    if (label != null) {
        // Fill in the data pattern with the args to build a valid URI
        val title = StringBuffer()
        val fillInPattern = Pattern.compile("\\{(.+?)\\}")
        val matcher = fillInPattern.matcher(label)
        while (matcher.find()) {
            val argName = matcher.group(1)
            if (arguments != null && arguments.containsKey(argName)) {
                matcher.appendReplacement(title, "")
                title.append(arguments.get(argName).toString())
            } else {
                return //returning because the argument required is not found
            }
        }
        matcher.appendTail(title)
        setTitle("")
        toolbarTxt.text = title
    }
}
