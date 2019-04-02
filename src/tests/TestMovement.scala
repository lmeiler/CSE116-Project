package tests
import main._
import objects.{Projectile, Shoot}
import physics._
import org.scalatest._

class TestMovement extends FunSuite{
  test("Test x direction movement"){
    val defaultHealth: Int = 20
    val defaultStrength: Int = 5
    val Location: PhysicsVector = new PhysicsVector(5.0, 0.0)
    val Velocity: PhysicsVector = new PhysicsVector(3.0, 0.0)
    val Orientation: PhysicsVector = new PhysicsVector(1.0, 0.0)

    val player1 = new Player(Location, Velocity, "Player")
    player1.projectile = new Projectile(new PhysicsVector(0.0, 0.0), new PhysicsVector(0.0, 0.0))
    val direction1: PhysicsVector = new PhysicsVector(-1.0, 0.0)
    player1.player_movement(direction1)
    assert(player1.velocity.x == -5.0 )
    assert(player1.velocity.y == 0.0)
    assert(player1.orientation.x == -1.0)


  }

  test("Test y direction movement: Jump"){
    val defaultHealth: Int = 20
    val defaultStrength: Int = 5
    val Location: PhysicsVector = new PhysicsVector(5.0, 0.0)
    val Velocity: PhysicsVector = new PhysicsVector(3.0, 0.0)
    val Orientation: PhysicsVector = new PhysicsVector(1.0, 0.0)

    val player1 = new Player(Location, Velocity, "Player")
    player1.projectile = new Projectile(new PhysicsVector(0.0, 0.0), new PhysicsVector(0.0, 0.0))
    val direction1: PhysicsVector = new PhysicsVector(0.0, 1.0)
    player1.player_movement(direction1)
    assert(player1.velocity.x == 0.0 )
    assert(player1.velocity.y == 5.0)
    assert(player1.orientation.x == 0.0)
    assert(player1.orientation.y == 1.0)
  }

}
