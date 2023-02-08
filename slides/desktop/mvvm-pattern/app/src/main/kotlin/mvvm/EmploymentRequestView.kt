/*
 * Copyright 2016 Bekwam, Inc
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package mvvm

import javafx.application.Platform
import javafx.beans.binding.Bindings
import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.geometry.Insets
import javafx.geometry.Pos
import javafx.scene.control.*
import javafx.scene.layout.ColumnConstraints
import javafx.scene.layout.GridPane
import javafx.scene.layout.Priority
import javafx.scene.layout.VBox
import javafx.util.converter.NumberStringConverter

/**
 * @author carl
 */
class EmploymentRequestView : VBox() {
    private val gp = GridPane()
    private val tfName = TextField()
    private val tfPosition = TextField()
    private val tfAnnualSalary = TextField()
    private val btnSave = Button("Save")
    private val btnCancel = Button("Cancel")
    private val btnReset = Button("Reset")
    private val viewModel: EmploymentRequestViewModel = EmploymentRequestViewModel()

    init {
        createView()
        bindViewModel()
    }

    private fun createView() {
        val gpwrap = VBox()
        gpwrap.alignment = Pos.CENTER
        gp.padding = Insets(40.0)
        gp.vgap = 4.0
        gp.add(Label("Name"), 0, 0)
        gp.add(tfName, 1, 0)
        gp.add(Label("Desired Position"), 0, 1)
        gp.add(tfPosition, 1, 1)
        gp.add(Label("Current Annual Salary"), 0, 2)
        gp.add(tfAnnualSalary, 1, 2)
        val col = ColumnConstraints()
        col.percentWidth = 50.0
        gp.columnConstraints.addAll(col, col)
        gpwrap.children.add(gp)
        setVgrow(gpwrap, Priority.ALWAYS)
        btnSave.onAction = EventHandler { evt: ActionEvent -> save(evt) }
        btnCancel.onAction = EventHandler { evt: ActionEvent -> cancel(evt) }
        btnReset.onAction = EventHandler { evt: ActionEvent -> reset(evt) }
        btnSave.isDefaultButton = true
        val buttonBar = ButtonBar()
        buttonBar.padding = Insets(20.0)
        ButtonBar.setButtonData(btnSave, ButtonBar.ButtonData.OK_DONE)
        ButtonBar.setButtonData(btnCancel, ButtonBar.ButtonData.CANCEL_CLOSE)
        ButtonBar.setButtonData(btnReset, ButtonBar.ButtonData.APPLY)
        buttonBar.buttons.addAll(btnSave, btnCancel, btnReset)
        children.addAll(
            gpwrap,
            Separator(),
            buttonBar
        )
    }

    private fun bindViewModel() {
        tfName.textProperty().bindBidirectional(viewModel.nameProperty())
        tfPosition.textProperty().bindBidirectional(viewModel.positionProperty())
        Bindings.bindBidirectional(
            tfAnnualSalary.textProperty(),
            viewModel.annualSalaryProperty(),
            NumberStringConverter()
        )
    }

    private fun save(evt: ActionEvent) {
        viewModel.save()
    }

    private fun cancel(evt: ActionEvent) {
        Platform.exit()
    }

    private fun reset(evt: ActionEvent) {
        viewModel.reset()
    }
}