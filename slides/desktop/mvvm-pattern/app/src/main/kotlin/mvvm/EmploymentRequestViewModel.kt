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

import javafx.beans.property.DoubleProperty
import javafx.beans.property.SimpleDoubleProperty
import javafx.beans.property.SimpleStringProperty
import javafx.beans.property.StringProperty

/**
 * @author carl
 */
class EmploymentRequestViewModel {
    private val name: StringProperty = SimpleStringProperty("")
    private val position: StringProperty = SimpleStringProperty("")
    private val annualSalary: DoubleProperty = SimpleDoubleProperty()
    private val converter = EmploymentRequestConverter()
    private val model = EmploymentRequestModel()
    fun getName(): String {
        return name.get()
    }

    fun nameProperty(): StringProperty {
        return name
    }

    fun setName(name: String) {
        this.name.set(name)
    }

    fun getPosition(): String {
        return position.get()
    }

    fun positionProperty(): StringProperty {
        return position
    }

    fun setPosition(position: String) {
        this.position.set(position)
    }

    fun getAnnualSalary(): Double {
        return annualSalary.get()
    }

    fun annualSalaryProperty(): DoubleProperty {
        return annualSalary
    }

    fun setAnnualSalary(annualSalary: Double) {
        this.annualSalary.set(annualSalary)
    }

    fun save() {
        val data = converter.toEmploymentRequest(this)
        model.save(data)
    }

    fun reset() {
        name.set("")
        position.set("")
        annualSalary.set(0.0)
    }
}