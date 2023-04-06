public enum Zone {
    North,
    Center,
    south
}


/**
    a few things needs to be done.

        1. complete the change truck function. changeTruck() (i wrote the call)
        this function is called when there is overweight in the executeShipment method, and the user chose to switch a truck
        basically this function suppose to find a new truck, only if the truck is bigger then the one we have at the moment,
        so just check the size of the new truck and compare it with the old one.

        then search for a new driver if the driver cant use this truck, and make the old driver free at that day,
        the function search for driver only update the new one.
        then modify the current shipment by assigning new truck and driver(if needed)

        2. add what ever function u need to the classes just don't forgot to update the main branch so there won't be any problems.

        3. add prints to the classes that doesn't have one.
*/