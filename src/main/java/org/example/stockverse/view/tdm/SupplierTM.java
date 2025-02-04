package org.example.stockverse.view.tdm;

public class SupplierTM implements Comparable<SupplierTM> {

        private String Sup_Id;
        private String Name;
        private Integer Contact;
        private String User_Id;


        public SupplierTM(String Sup_Id, String Name, Integer Contact,String User_Id) {
            this.Sup_Id = Sup_Id;
            this.Name =Name;
            this.Contact = Contact;
            this.User_Id =User_Id;
        }
        public String getSup_Id() {
            return Sup_Id;
        }
        public void setSup_Id(String Sup_Id) {
            this.Sup_Id = Sup_Id;
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
            return "SupplierTM{" +
                    "Sup_Id='" + Sup_Id + '\''+
                    ",Name='" + Name +'\'' +
                    ", Contact='" + Contact + '\'' +
                    ", User_Id='" + User_Id + '\'' +
                    '}' ;
        }

    @Override
    public int compareTo(SupplierTM o) {
        return 0;
    }
}

