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

import javafx.application.Application
import javafx.scene.Scene
import javafx.stage.Stage

/**
 * @author carl
 */
class MVVMApp : Application() {
    override fun start(primaryStage: Stage) {
        val view = EmploymentRequestView()
        val scene = Scene(view)
        primaryStage.title = "MVVM App"
        primaryStage.scene = scene
        primaryStage.width = 480.0
        primaryStage.height = 320.0
        primaryStage.show()
    }
}