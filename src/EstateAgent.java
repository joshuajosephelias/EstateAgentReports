
    public class EstateAgent implements IEstateAgent {

        // Commission = price * percentage / 100
        @Override
        public double CalculateCommission(String propertyPrice, String agentCommission) {
            double price = Double.parseDouble(propertyPrice);
            double percentage = Double.parseDouble(agentCommission);
            return price * percentage / 100;
        }

        // Location and name must not be empty; price and commission must be > 0
        @Override
        public boolean ValidateData(Data d) {
            if (d.getLocation() == null || d.getLocation().trim().isEmpty()) return false;
            if (d.getName() == null || d.getName().trim().isEmpty()) return false;
            try {
                if (Double.parseDouble(d.getPrice()) <= 0) return false;
                if (Double.parseDouble(d.getCommission()) <= 0) return false;
            } catch (NumberFormatException ex) {
                return false;   // not a number
            }
            return true;
        }
    }
