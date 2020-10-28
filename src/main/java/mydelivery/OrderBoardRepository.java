package mydelivery;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderBoardRepository extends CrudRepository<OrderBoard, Long> {


}