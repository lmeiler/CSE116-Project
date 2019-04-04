package view

import controller.PressMovement
import javafx.scene.input.{KeyEvent, MouseEvent}
import main.Player
import physics.{Boundary, PhysicsVector, World}
import scalafx.animation.AnimationTimer
import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.scene.paint.Color
import scalafx.scene.shape.{Circle, Rectangle, Shape}
import scalafx.scene.{Group, Scene}

import scala.collection.mutable
import scala.collection.mutable.ListBuffer


object Game1 extends JFXApp {
  var lastUpdateTime: Long = System.nanoTime()

  var sceneGraphics: Group = new Group {}
  var boundaries: List[Boundary] = List(new Boundary(new PhysicsVector(0, 2000), new PhysicsVector(2000, 2000)))
  var game = new World(boundaries)
  var player = new Player(new PhysicsVector(200, 100), new PhysicsVector(0, 0), "abc", game)
  val playerS: Shape = playerBody(player.location.x, player.location.y, Color.Blue)
  var playerBuffer: mutable.ListBuffer[Player] = ListBuffer[Player](player)
  game.players += player
  val players = game.players
  var listbullet:List[Shape]=List()

  def playerBody(xLocation: Double, yLocation: Double, color: Color): Shape = {
    new Rectangle {
      width = 3 * 10
      height = 5 * 10
      translateX = xLocation
      translateY = yLocation
      fill = color
    }
  }


    def bulletBody(xLocation: Double, yLocation: Double, color: Color): Unit= {
      var bullet = new Circle {
        centerX = xLocation
        centerY = yLocation
        radius = 10.0
        fill = Color.Green
      }
      player.shoot()
      sceneGraphics.children.add(bullet)
      listbullet = bullet :: listbullet
    }


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

  rect.fill = Color.Green
  sceneGraphics.children.add(rect)
  sceneGraphics.children.add(obs1)
  sceneGraphics.children.add(obs2)
  sceneGraphics.children.add(obs5)
  sceneGraphics.children.add(obs4)
  sceneGraphics.children.add(obs6)
  sceneGraphics.children.add(obs7)
  sceneGraphics.children.add(obs8)
  sceneGraphics.children.add(obs9)
  sceneGraphics.children.add(obs11)
  sceneGraphics.children.add(obs12)
  sceneGraphics.children.add(obs13)
  sceneGraphics.children.add(obs14)
  sceneGraphics.children.add(obs17)
  sceneGraphics.children.add(obs20)
  sceneGraphics.children.add(obs21)
  sceneGraphics.children.add(obs22)
  sceneGraphics.children.add(playerS)

  this.stage = new PrimaryStage {
    this.title = "Battle World"
    scene = new Scene(2000, 2000) {

      content = List(sceneGraphics)
      //      content = List(rect, obs1, obs2, obs4, obs5, obs6, obs7, obs8, obs9, obs11, obs12, obs13, obs14, obs15, obs17, obs20, obs21, obs22, playerSprite)

      addEventHandler(KeyEvent.KEY_PRESSED, new PressMovement(player))
      addEventHandler(KeyEvent.KEY_RELEASED, new PressMovement(player))
      addEventHandler(MouseEvent.MOUSE_CLICKED, (event: MouseEvent) => bulletBody(game.players.head.location.x, game.players.head.location.y,Color.Black))

    }
    val update: Long => Unit = (time: Long) => {

      val deltatime: Double = (time - lastUpdateTime) / 1000000000.0
      lastUpdateTime = time
      game.update(deltatime)

      playerS.translateX.value = game.players.head.location.x
      playerS.translateY.value = game.players.head.location.y
      if(game.projectiles.isEmpty){

      }
      else{
          for (i <- listbullet) {
            i.translateX.value+=5
            i.translateY.value=5

          }
        }

    }
    AnimationTimer(update).start()
  }




}


