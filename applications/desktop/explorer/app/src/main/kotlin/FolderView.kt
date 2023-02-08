import javafx.scene.Node
import javafx.scene.control.TreeItem
import javafx.scene.control.TreeView
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import javafx.scene.layout.StackPane

class FolderView {
    fun build() : StackPane {
        val folderIcon: Node = ImageView(Image(javaClass.getResourceAsStream("folder.png")))
        val rootItem = TreeItem<Any?>("Inbox", folderIcon)
        rootItem.isExpanded = true
        for (i in 1..5) {
            val item = TreeItem<Any?>("Message$i")
            rootItem.children.add(item)
        }
        return StackPane(TreeView<Any?>(rootItem))
    }
}