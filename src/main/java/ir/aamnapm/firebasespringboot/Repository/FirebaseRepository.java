package ir.aamnapm.firebasespringboot.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ir.aamnapm.firebasespringboot.Module.Firebase;

public interface FirebaseRepository extends JpaRepository<Firebase, Integer> {

  Firebase findByNotifyId(String notifyId);
}
