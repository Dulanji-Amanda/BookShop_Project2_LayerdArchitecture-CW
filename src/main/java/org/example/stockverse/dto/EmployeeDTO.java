package org.example.stockverse.dto;

import java.io.Serializable;

    public class EmployeeDTO implements Serializable {
        private String Emp_Id;
        private String Name;
        private Integer Contact;
        private String User_Id;

        public EmployeeDTO() {
        }

        public EmployeeDTO(String Emp_Id, String Name, Integer Contact,String User_Id) {
            this.Emp_Id = Emp_Id;
            this.Name =Name;
            this.Contact = Contact;
            this.User_Id =User_Id;
        }
        public String getEmp_Id() {
            return Emp_Id;
        }
        public void setEmp_Id(String Emp_Id) {
            this.Emp_Id = Emp_Id;
        }
        public String getName() {
            return Name;
        }
        public void setName(String Name) {
            this.Name = Name;
        }
        public Integer getContact() {
            return Contact;
        }
        public void setContact(Integer Contact) {
            this.Contact = Contact;
        }
        public String getUser_Id() {
            return User_Id;
        }
        public void setUser_Id(String User_Id) {
            this.User_Id = User_Id;
        }

        @Override
        public String toString() {
            return "EmployeeDTO{" +
                    "Emp_Id='" + Emp_Id + '\''+
                    ",Name='" + Name +'\'' +
                    ", Contact='" + Contact + '\'' +
                    ", User_Id='" + User_Id + '\'' +
                    '}' ;
        }
    }

