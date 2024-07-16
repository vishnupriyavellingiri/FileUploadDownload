package FileToDataUpload.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import FileToDataUpload.Entity.UserDetails;

public interface UserRepository extends JpaRepository<UserDetails,Long>{

}
