#include <iostream>
#include "EventCollection.h"

int main()
{
	EventCollection ec;

	ec.addEvent(Event("event 1", Date(1, 2, 2000), Time(15,2,3), Time(65,5,9)));

	ec.addEvent(Event("event 2", Date(1, 3, 2000), Time(25, 2, 3), Time(65, 4, 9)));

	ec.addEvent(Event("event 3", Date(1, 2, 2015), Time(15, 2, 3), Time(65, 5, 9)));

	ec.addEvent(Event("event 4", Date(1, 2, 2000), Time(15, 3, 3), Time(65, 6, 9)));

	ec.print();

	std::cout << "Get longest event name: " << ec.getLongestEvent().getName() << std::endl;

	std::cout << "Max count of events: " << ec.maxCountOfEvents(Date(1,2,2000)) << std::endl;

	ec.removeEvent("event 4");

	ec.print();
}