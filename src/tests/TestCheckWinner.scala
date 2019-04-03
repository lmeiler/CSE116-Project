package tests

import main._
import physics._
import org.scalatest._

class TestCheckWinner extends FunSuite {

  test("Tests if there is one player left in the game, and returns the username if so. Else, return filler statement") {
    val defaultHealth: Int = 20
    val defaultStrength: Int = 5
    val defaultLocation: PhysicsVector = new PhysicsVector(0.0, 0.0)
    val defaultVelocity: PhysicsVector = new PhysicsVector(0.0, 0.0)
    val defaultOrientation: PhysicsVector = new PhysicsVector(1.0, 0.0)

    val boundaries: List[Boundary] = List.empty

    val world1: World = new World(boundaries)
    val world2: World = new World(boundaries)
    val world3: World = new World(boundaries)

    val Gerard1: Player = new Player(defaultLocation, defaultVelocity, "GerardOfRivertown", world1)
//    val deadGuy1: Player = new Player(defaultLocation, defaultVelocity, "xX_DeadGuy_Xx", world1)

    val Gerard2: Player = new Player(defaultLocation, defaultVelocity, "GerardOfRivertown", world2)
    val deadGuy2: Player = new Player(defaultLocation, defaultVelocity, "xX_DeadGuy_Xx", world2)

//    val Gerard3: Player = new Player(defaultLocation, defaultVelocity, "GerardOfRivertown", world3)
//    val deadGuy3: Player = new Player(defaultLocation, defaultVelocity, "xX_DeadGuy_Xx", world3)


    world1.players += Gerard1

    world2.players += Gerard1
    world2.players += deadGuy2

    assert(world1.checkWinner() == "GerardOfRivertown has won!!")
    assert(world2.checkWinner() == "No Winner Yet")
    assert(world3.checkWinner() == "Draw")
  }
}
