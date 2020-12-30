package fp.utilities
import doobie.implicits.toSqlInterpolator
import fp.model.{Airport, Country, Runway}

import scala.io.Source
import scala.reflect.ClassTag
import scala.swing._
import scala.swing.event.{ButtonClicked, Event}

object Gui {

  val runway_file = "resources/runways_test.csv"
  val airport_file = "resources/airports_test.csv"
  val country_file = "resources/countries.csv"

  class Menu extends MainFrame {
    title = "AirViewer"
    preferredSize = new Dimension(500, 500)
    contents = new BoxPanel(Orientation.Vertical) {
      contents += new Label("Welcome to AirViewer !")
      contents += Button("Parse and begin") {
        fp.utilities.Sql.Create_tables()
        fp.utilities.Parse.parseCsvFile(airport_file,Airport.fromStrings,Airport.toSqlStr)
        fp.utilities.Parse.parseCsvFile(country_file,Country.fromStrings,Country.toSqlStr)
        fp.utilities.Parse.parseCsvFile(runway_file,Runway.fromStrings,Runway.toSqlStr)
        Gui.toSelBoard()
        Menu.this.visible = false }
      contents += Button("Begin without parsing") {
        Gui.toSelBoard()
        Menu.this.visible = false }
      contents += Button("Close") { sys.exit(0) }
      border = Swing.EmptyBorder(125, 125, 125, 125)
    }
  }

  class SelBoard extends MainFrame {
    title = "AirViewer"
    preferredSize = new Dimension(500, 500)
    contents = new BoxPanel(Orientation.Vertical) {
      contents += Button("Query") {toQuery();SelBoard.this.visible = false}
      contents += Button("Reports") {toReport();SelBoard.this.visible = false}
      contents += Button("Close") { sys.exit(0) }
      border = Swing.EmptyBorder(125, 125, 125, 125)
    }
  }

  class Query extends MainFrame{
    title = "AirViewer"
    preferredSize = new Dimension(500, 500)
    val QueryButton_1 = new Button("Country Name")
    val QueryButton_2 = new Button("Country Code")
    val textField = new TextField(text = "Country Name or Country Code")
    contents = new BoxPanel(Orientation.Vertical) {
      contents += QueryButton_1
      contents += QueryButton_2
      contents += textField
      border = Swing.EmptyBorder(125, 125, 125, 125)
      listenTo(QueryButton_1,QueryButton_2)
      reactions+={
        case ButtonClicked(QueryButton_1) => QueryButton1Clicked(textField.text)
        case ButtonClicked(QueryButton_2) => QueryButton2Clicked(textField.text)
      }
    }

  }

  class ResultQuery(list: List[String]) extends MainFrame{
    title = "AirViewer"
    preferredSize = new Dimension(500, 500)
    val Combox = new ComboBox[String](list)
    val Button = new Button("Refresh")
    val Label = new Label(fp.utilities.Sql.GetRunways(fp.utilities.Sql.NameToID(Combox.item)).mkString("\n"))
    contents = new BoxPanel(Orientation.Vertical) {
      contents += Combox
      contents += Button
      contents += Label
      listenTo(Button)
      reactions+={
        case ButtonClicked(Button)=>{Label.text = fp.utilities.Sql.GetRunways(fp.utilities.Sql.NameToID(Combox.item)).mkString("\n")}
      }
      border = Swing.EmptyBorder(125, 125, 125, 125)
    }
  }

  class Report extends MainFrame{
    title = "AirViewer"
    preferredSize = new Dimension(500, 500)
    val Airport_nb = new Button("Number of airports")
    val Runway_tp = new Button("Types of runways")
    val Runway_com = new Button("Common runways")
    contents = new BoxPanel(Orientation.Vertical) {
      contents += Airport_nb
      contents += Runway_tp
      contents += Runway_com
      border = Swing.EmptyBorder(125, 125, 125, 125)
      listenTo(Airport_nb,Runway_tp,Runway_com)
      reactions+={
        case ButtonClicked(Airport_nb) =>{Report.this.visible = false;toResultReport("ResultAiportNb")}
        case ButtonClicked(Runway_tp) =>{Report.this.visible = false;toResultReport("ResultRunwayTp")}
        case ButtonClicked(Runway_com) =>{Report.this.visible = false;toResultReport("ResultRunwayCom")}
      }

    }
  }

  class ResultAiportNb extends MainFrame{
    title = "AirViewer"
    preferredSize = new Dimension(500, 500)
    val Combox = new ComboBox[String](List("Highest number of airports","Lowest number of airports"))
    val Button = new Button("Refresh")
    val Label = new Label(fp.utilities.Sql.SelResultAirportNb(Combox.item))
    contents = new BoxPanel(Orientation.Vertical) {
      border = Swing.EmptyBorder(125, 125, 125, 125)
      contents += Combox
      contents += Button
      contents += Label
      listenTo(Button)
      reactions+={
        case ButtonClicked(Button) => {Label.text=fp.utilities.Sql.SelResultAirportNb(Combox.item)}
      }
    }
  }

  class ResultRunwayTp extends MainFrame{
    title = "AirViewer"
    preferredSize = new Dimension(500, 500)
  }

  class ResultRunwayCom extends MainFrame{
    title = "AirViewer"
    preferredSize = new Dimension(500, 500)
  }


  def QueryButton1Clicked(string: String): Unit ={
    val iso = fp.utilities.Sql.NameToIso(string)
    val list = fp.utilities.Sql.QuerySearch(iso)
    list match {
      case Nil => None
      case _ => toResultQuery(list)
    }
  }
  def QueryButton2Clicked(string: String): Unit ={
    val list = fp.utilities.Sql.QuerySearch(string)
    list match {
      case Nil => None
      case _ => toResultQuery(list)
    }
  }


  def display() ={
    val ui = new Menu
    ui.visible = true
  }
  def toSelBoard() ={
    val ui = new SelBoard
    ui.visible = true
  }
  def toQuery() ={
    val ui = new Query
    ui.visible = true
  }
  def toResultQuery(list: List[String]) ={
    val ui = new ResultQuery(list)
    ui.visible = true
  }
  def toReport()={
    val ui = new Report
    ui.visible = true
  }
  def toResultReport(source: String)={
    source match {
      case "ResultAiportNb"=>{val ui = new ResultAiportNb;ui.visible = true}
      case "ResultRunwayTp"=>{val ui = new ResultRunwayTp;ui.visible = true}
      case "ResultRunwayCom"=>{val ui = new ResultRunwayCom;ui.visible = true}
    }
  }
}

