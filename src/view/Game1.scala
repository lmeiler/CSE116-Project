package view

import controller.PressMovement
import javafx.scene.input.KeyEvent
import main.Player
import physics.{Boundary, PhysicsVector, World}
import scalafx.animation.AnimationTimer
import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.scene.canvas.Canvas
import scalafx.scene.paint.Color
import scalafx.scene.shape.{Rectangle, Shape}
import scalafx.scene.{Group, Scene}

import scala.collection.mutable
import scala.collection.mutable.ListBuffer


object Game1 extends JFXApp {
  var lastUpdateTime: Long = System.nanoTime()
  val scale_f: Double = 10.0
  val windowWidth: Double = 30 * scale_f
  val windowHeight: Double = 30 * scale_f
  var sceneGraphics: Group = new Group {}
  var boundaries: List[Boundary] = List()
  var game = new World(boundaries,player)
  var player = new Player(new PhysicsVector(0, 1), new PhysicsVector(1, 1), "abc")
  val playerSprite: Shape = playerSprite(player.location.x, player.location.y, Color.Blue)
  var playerBuffer: mutable.ListBuffer[Player] = ListBuffer[Player](player)
  game.players += player

  def computeDistance(v1: PhysicsVector, v2: PhysicsVector): Double = {
    Math.sqrt(Math.pow(v1.x - v2.x, 2.0) + Math.pow(v1.y - v2.y, 2.0))
  }

  def convertX(gameX: Double, width: Double): Double = {
    gameX* scale_f
  }

  def convertY(gameY: Double, height: Double): Double = {
    gameY  * scale_f
  }

  def playerSprite(xLocation: Double, yLocation: Double, color: Color): Shape = {
    new Rectangle {
      width = 3 * scale_f
      height = 5 * scale_f
      translateX = convertX(xLocation,10)
      translateY = convertY(yLocation,0)
      fill = color
    }
  }

  this.stage = new PrimaryStage {
    this.title = "Battle World"
    scene = new Scene(2000, 2000) {

      var rect = Rectangle(5, 590, 2000, 200)
      var obs1 = Rectangle(10, 200, 300, 10)
      var obs2 = Rectangle(50, 300, 200, 10)
      var obs4 = Rectangle(450, 256, 270, 10)
      var obs5 = Rectangle(650, 440, 300, 10)
      var obs6 = Rectangle(700, 200, 300, 10)
      var obs7 = Rectangle(850, 150, 270, 10)
      var obs8 = Rectangle(456, 350, 270, 10)
      var obs9 = Rectangle(850, 300, 270, 10)
      var obs11 = Rectangle(1050, 185, 270, 10)
      var obs12 = Rectangle(20, 404, 270, 10)
      var obs13 = Rectangle(1200, 300, 370, 10)
      var obs14 = Rectangle(1100, 75, 350, 10)
      var obs15 = Rectangle(5, 54, 300, 10)
      var obs17 = Rectangle(1200, 450, 270, 10)
      var obs20 = Rectangle(50, 500, 270, 10)
      var obs21 = Rectangle(545, 50, 270, 10)
      var obs22 = Rectangle(1050, 380, 270, 10)
      obs1.fill = Color.Red
      rect.fill = Color.Green
      content = List(rect, obs1, obs2, obs4, obs5, obs6, obs7, obs8, obs9, obs11, obs12, obs13, obs14, obs15, obs17, obs20, obs21, obs22, playerSprite)
      addEventHandler(KeyEvent.KEY_PRESSED, new PressMovement(player))

    }
    val update: Long => Unit = (time: Long) => {
      val dt: Double = (time - lastUpdateTime) / 1000000000.0
      lastUpdateTime = time
      game.update(dt)
      playerSprite.translateX.value = 100
//      playerSprite.translateY.value = 10

    }
    AnimationTimer(update).start()

  }
}
