package com.tusia.practise.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class Item {

        @Id(autoincrement = true)
        private Long id;

        private String name;
        private String description;


        @Generated(hash = 652316871)
        public Item(Long id, String name, String description) {
            this.id = id;
            this.name = name;
            this.description = description;
        }
        @Generated(hash = 1470900980)
        public Item() {
        }


        public Long getId() {
            return this.id;
        }
        public void setId(Long id) {
            this.id = id;
        }
        public String getName() {
            return this.name;
        }
        public void setName(String name) {
            this.name = name;
        }
        public String getDescription() {
            return this.description;
        }
        public void setDescription(String description) {
            this.description = description;
        }

}
