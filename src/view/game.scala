package view

import java.awt.Button
import main.Player
import physics.PhysicsVector
import scalafx.scene.{Group, Scene}
import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.geometry.Insets
import scalafx.scene.Scene
import scalafx.scene.canvas.Canvas
import scalafx.scene.effect.DropShadow
import scalafx.scene.layout.HBox
import scalafx.scene.paint.Color._
import scalafx.scene.paint.{Color, LinearGradient, Stops}
import scalafx.scene.shape.Rectangle
import scalafx.scene.text.Text



object game extends JFXApp {
  var p=new Player(new PhysicsVector(0,1),new PhysicsVector(0,1),"demo2")
  stage = new PrimaryStage {
    title = "Battle World"
    scene = new Scene(2000,2000) {

      var canvas = new Canvas(2000, 2000)
      var g = canvas.getGraphicsContext2D
      var rect = Rectangle(5,590,2000, 200)
      var obs1 = Rectangle(10,200, 300, 10)
      var obs2 = Rectangle(50,300, 200, 10)
      var obs4 = Rectangle(450,256, 270, 10)
      var obs5 = Rectangle(650,440, 300, 10)
      var obs6 = Rectangle(700,200, 300, 10)
      var obs7 = Rectangle(850,150, 270, 10)
      var obs8 = Rectangle(456,350, 270, 10)
      var obs9 = Rectangle(850,300, 270, 10)
      var obs11 = Rectangle(1050,185, 270, 10)
      var obs12 = Rectangle(20,404, 270, 10)
      var obs13 = Rectangle(1200,300, 370, 10)
      var obs14 = Rectangle(1100,75, 350, 10)
      var obs15 = Rectangle(5,54, 300, 10)
      var obs17 = Rectangle(1200,450, 270, 10)

      var obs20 = Rectangle(50,500, 270, 10)
      var obs21 = Rectangle(545,50, 270, 10)
      var obs22 = Rectangle(1050,380, 270, 10)



      rect.fill=Color.Green
      content=List(rect,obs1,obs2,obs4,obs5,obs6,obs7,obs8,obs9,obs11,obs12,obs13,obs14,obs15,obs17,obs20,obs21,obs22)

    }
  }
}


