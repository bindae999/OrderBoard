package mydelivery;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="OrderBoard_table")
public class OrderBoard {

        @Id
        @GeneratedValue(strategy=GenerationType.AUTO)
        private Long id;
        private String foodName;
        private Long foodStatus;
        private String orderStatus;


        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }
        public String getFoodName() {
            return foodName;
        }

        public void setFoodName(String foodName) {
            this.foodName = foodName;
        }
        public Long getFoodStatus() {
            return foodStatus;
        }

        public void setFoodStatus(Long foodStatus) {
            this.foodStatus = foodStatus;
        }
        public String getOrderStatus() {
            return orderStatus;
        }

        public void setOrderStatus(String orderStatus) {
            this.orderStatus = orderStatus;
        }

}
