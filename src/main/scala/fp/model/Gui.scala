package fp.model
import scala.swing._

class Menu extends MainFrame {
  title = "AirViewer"
  preferredSize = new Dimension(500, 500)
  contents = new BoxPanel(Orientation.Vertical) {
    contents += new Label("Welcome to AirViewer !")
    contents += Button("Begin") { GuiProgramOne.toSelBoard();Menu.this.visible = false }
    contents += Button("Close") { sys.exit(0) }
    border = Swing.EmptyBorder(125, 125, 125, 125)
  }
}

class SelBoard extends MainFrame {
  title = "AirViewer"
  preferredSize = new Dimension(500, 500)
  contents = new BoxPanel(Orientation.Vertical) {
    contents += Button("Fct1") { println("Thank you") }
    contents += Button("Fct2") { println("Thank you") }
    contents += Button("Close") { sys.exit(0) }
    border = Swing.EmptyBorder(125, 125, 125, 125)
  }
}

object GuiProgramOne {
  def display() {
    val ui1 = new Menu
    ui1.visible = true
  }
  def toSelBoard() ={
    val ui2 = new SelBoard
    ui2.visible = true
  }
}

