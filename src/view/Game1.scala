package view

import main.Player
import physics.{Boundary, PhysicsVector, World}
import scalafx.animation.AnimationTimer
import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.scene.paint.Color
import scalafx.scene.shape.{Rectangle, Shape}
import scalafx.scene.{Group, Scene}

object Game1 {
  var lastUpdateTime: Long = System.nanoTime()
  var player = new Player(new PhysicsVector(0, 1), new PhysicsVector(1, 1), "abc")
  val scale_f: Double = 30.0
  val windowWidth: Double = 30 * scale_f
  val windowHeight: Double = 30 * scale_f
  var sceneGraphics: Group = new Group {}
  var boundaries: List[Boundary] = List()
  var game = new World(9.8, boundaries, player,)
  val playerSprite: Shape = playerSprite(player.location.x,player.location.y, Color.Blue)

  def computeDistance(v1: PhysicsVector, v2: PhysicsVector): Double = {
    Math.sqrt(Math.pow(v1.x - v2.x, 2.0) + Math.pow(v1.y - v2.y, 2.0))
  }

  def convertX(gameX: Double, width: Double): Double = {
    (gameX - width / 2.0) * scale_f
  }

  def convertY(gameY: Double, height: Double): Double = {
    (30 - (gameY - 20) - height) * scale_f
  }

  def playerSprite(xLocation: Double, yLocation: Double, color: Color): Shape = {
    new Rectangle {
      width = 3 * scale_f
      height = 5 * scale_f
      translateX = convertX(xLocation, 3)
      translateY = convertY(yLocation, 5)
      fill = color
    }
  }

  val update: Long => Unit = (time: Long) => {
    val dt: Double = (time - lastUpdateTime) / 1000000000.0
    lastUpdateTime = time
    game.update()

    playerSprite.translateX.value = convertX(player.location.x, 10)
    playerSprite.translateY.value = convertY(player.location.y, 30)


  }
}
