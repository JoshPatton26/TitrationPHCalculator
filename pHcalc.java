import java.util.Scanner;

public class pHcalc{

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the number of the calculation"
                            +" you would like to perform:\n"+
                            "1. SA/SB \n2. WA/SB \n3. WB/SA");
        int input = scanner.nextInt();

        switch(input){
            
            case 1:
                sasb();
                break;

            case 2:
                wasb();
                break;

            case 3:
                wbsa();
                break;
            
            default:
                System.out.println("Your input was invalid, try again.");
        }
        scanner.close();
    }

    public static void sasb(){
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the volume (in mL) of analyte: ");
        double val1 = scanner.nextDouble();

        System.out.print("\nEnter the molarity of the analyte: ");
        double val2 = scanner.nextDouble();

        System.out.print("\nEnter the molarity of the titrant: ");
        double val3 = scanner.nextDouble();

        System.out.print("\nHow much titrant (in mL) has been added: ");
        double val4 = scanner.nextDouble();

        double newVal1 = (val1/1000)*val2;
        double newVal4 = (val4/1000)*val3;

        if(val4 == 0){
            double pH = Math.log10(1/val2);
            System.out.println("The pH = "+ (double)Math.round(pH * 1000)/1000);
        }else if(newVal4 < newVal1){
            double molAcid = newVal1 - newVal4;
            double totalVol = (val1+val4)/1000; 
            double molarity = molAcid / totalVol;
            double pH = Math.log10(1/molarity);
            System.out.println("The pH = "+ (double)Math.round(pH * 1000)/1000);
        }else if(newVal4 == newVal1){
            System.out.println("The pH = 7.000");
        }else if(newVal4 > newVal1){
            double molBaseExcess = newVal4 - newVal1;
            double totalVol = (val1+val4)/1000;
            double molarity = molBaseExcess / totalVol;
            double pOH = Math.log10(1/molarity);
            double pH = 14 - pOH;
            System.out.println("The pH = "+ (double)Math.round(pH * 1000)/1000);
        }
        scanner.close();
    }

    public static void wasb(){
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the volume (in mL) of analyte: ");
        double val1 = scanner.nextDouble();

        System.out.print("\nEnter the molarity of the analyte: ");
        double val2 = scanner.nextDouble();

        System.out.print("\nEnter the molarity of the titrant: ");
        double val3 = scanner.nextDouble();

        System.out.print("\nHow much titrant (in mL) has been added: ");
        double val4 = scanner.nextDouble();

        System.out.print("\nEnter your Ka value (in decimal form): ");
        double val5 = scanner.nextDouble();

        double newVal1 = (val1/1000)*val2;
        double newVal4 = (val4/1000)*val3;

        if(val4 == 0){
            double hydronium = val2 * val5;
            double conc = Math.sqrt(hydronium);
            double pH = Math.log10(1/conc);
            System.out.println("\nThe pH = "+ (double)Math.round(pH * 1000)/1000);
        }else if (newVal4 < newVal1){
            double molAcid = newVal1 - newVal4;
            double pH = Math.log10(1/val5) + Math.log10(newVal4/molAcid);
            System.out.print("\nThe pH = "+ (double)Math.round(pH * 1000)/1000);
        }else if(newVal4 == newVal1){
            double kb = 0.00000000000001/val5;
            double totalVol = (val1+val4)/1000;
            double baseMolarity = newVal4/totalVol;
            double hydronium = kb*baseMolarity;
            double conc = Math.sqrt(hydronium);
            double pOH = Math.log10(1/conc);
            double pH = 14 - pOH;
            System.out.print("\nThe pH = "+ (double)Math.round(pH * 1000)/1000);
        }else{
            double molBaseExcess = newVal4 - newVal1;
            double totalVol = (val1+val4)/1000;
            double baseMolarity = molBaseExcess/totalVol;
            double pOH = Math.log10(1/baseMolarity);
            double pH = 14 - pOH;
            System.out.println("\nThe pH = "+ (double)Math.round(pH * 1000)/1000);
        }
        scanner.close();
    }

    public static void wbsa(){
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the volume (in mL) of analyte: ");
        double val1 = scanner.nextDouble();

        System.out.print("\nEnter the molarity of the analyte: ");
        double val2 = scanner.nextDouble();

        System.out.print("\nEnter the molarity of the titrant: ");
        double val3 = scanner.nextDouble();

        System.out.print("\nHow much titrant (in mL) has been added: ");
        double val4 = scanner.nextDouble();

        System.out.print("\nEnter your Kb value (in decimal form): ");
        double val5 = scanner.nextDouble();

        double newVal1 = (double)Math.round(((val1/1000)*val2) * 10000)/10000;
        double newVal4 = (double)Math.round(((val4/1000)*val3) * 10000)/10000;
        double ka = 0.00000000000001/val5;

        if(val4 == 0){
            double hydroxide = val2 * val5;
            double conc = Math.sqrt(hydroxide);
            double pOH = Math.log10(1/conc);
            double pH = 14 - pOH;
            System.out.println("The pH = "+ (double)Math.round(pH * 1000)/1000);
        }else if(newVal4 < newVal1){
            double molBase = newVal1 - newVal4;
            double pH = Math.log10(1/ka) + Math.log10(molBase/newVal4);
            System.out.print("\nThe pH = "+ (double)Math.round(pH * 1000)/1000);
        }else if(newVal4 == newVal1){
            double totalVol = (val1+val4)/1000;
            double baseMolarity = newVal4/totalVol;
            double hydroxide = ka*baseMolarity;
            double conc = Math.sqrt(hydroxide);
            double pH = Math.log10(1/conc);
            System.out.println("The pH = "+ (double)Math.round(pH * 1000)/1000);
        }else{
            double molAcidExcess = newVal4 - newVal1;
            double totalVol = (val1+val4)/1000;
            double acidMolarity = molAcidExcess/totalVol;
            double pH = Math.log10(1/acidMolarity);
            System.out.println("The pH = "+ (double)Math.round(pH * 1000)/1000);
        }
        scanner.close();
    }
}