
    public class Data {
        private String location;
        private String name;
        private String price;
        private String commission;

        public Data(String location, String name, String price, String commission) {
            this.location = location;
            this.name = name;
            this.price = price;
            this.commission = commission;
        }

        public String getLocation() { return location; }
        public String getName() { return name; }
        public String getPrice() { return price; }
        public String getCommission() { return commission; }
    }


