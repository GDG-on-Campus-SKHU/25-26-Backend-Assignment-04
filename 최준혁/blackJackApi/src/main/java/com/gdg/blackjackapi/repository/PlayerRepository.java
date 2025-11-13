package com.gdg.blackjackapi.repository;

import com.gdg.blackjackapi.domain.Player.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<Player, Long> {

}
