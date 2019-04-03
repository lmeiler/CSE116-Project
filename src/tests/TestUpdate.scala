package tests
import main._
import objects.{Projectile, Shoot}
import physics._
import org.scalatest._

class TestUpdate extends FunSuite {
  test("Test update"){
    val boundary1 = new Boundary(new PhysicsVector(20, 150), new PhysicsVector(40, 150))
    val boundary2 = new Boundary(new PhysicsVector(40, 200), new PhysicsVector(60, 200))
    var boundaries: List[Boundary] = List(boundary1, boundary2)
    val world = new World(boundaries)
    var player1: Player = new Player(new PhysicsVector(10, 0), new PhysicsVector(0, 0), "Player1", world)
    var player2: Player = new Player(new PhysicsVector(70, 0), new PhysicsVector(0, 0), "Player2", world)

    val players: List[Player] = List(player1, player2)
    world.players += player1
    world.players += player2

    // Press left to player1
    player1.rightPressed()
    player2.leftPressed()

    world.update(.45)


    val player1_newLocation: PhysicsVector = new PhysicsVector(235, 0)
    val player2_newLocation: PhysicsVector = new PhysicsVector(-155, 0)
    val player1_velocity: PhysicsVector = new PhysicsVector(500, 0)
    val player2_velocity: PhysicsVector = new PhysicsVector(-500, 0)
    assert(player1.velocity.x == player1_velocity.x)
    assert(player2.velocity.x == player2_velocity.x)
    assert(player1.location.x == player1_newLocation.x)
    assert(player2.location.x == player2_newLocation.x)



  }


}
