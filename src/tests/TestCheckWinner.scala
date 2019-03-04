package tests

import main._
import physics._
import org.scalatest._

class TestCheckWinner extends FunSuite {

  test("Tests if there is one player left in the game, and returns the username if so. Else, return filler statement") {
    val defaultHealth: Int = 20
    val defaultStrength: Int = 5
    val defaultLocation: PhysicsVector = new PhysicsVector(0.0, 0.0, 0.0)
    val defaultVelocity: PhysicsVector = new PhysicsVector(0.0, 0.0, 0.0)
    val defaultOrientation: PhysicsVector = new PhysicsVector(1.0, 0.0, 0.0)

    val Gerard: Player = new Player(defaultHealth, defaultStrength, defaultLocation, defaultVelocity, defaultOrientation, "GerardOfRivertown", defaultHealth)
    val Jerk: Player = new Player(defaultHealth, defaultStrength, defaultLocation, defaultVelocity, defaultOrientation, "SomeOtherJerk", defaultHealth)

    val playerList: List[Player] = List(Gerard, Jerk)
    val world1: World = new World(9.8, List(), List(), List(Gerard), List())
    val world2: World = new World(9.8, List(), List(), List(Gerard, Jerk), List())

    assert(world1.checkWinner(world1.players) == "GerardOfRivertown has won!!")
    assert(world2.checkWinner(world2.players) == "No Winner Yet")
  }
}
