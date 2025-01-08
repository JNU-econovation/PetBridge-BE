package PetBridge.auth.jwt.repository;

import PetBridge.auth.jwt.entity.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
}
