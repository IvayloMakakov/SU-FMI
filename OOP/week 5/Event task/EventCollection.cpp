#include "EventCollection.h"

void EventCollection::addEvent(const Event& event)
{
	if (count >= 20)
		return;

	events[count++] = event;
}

const Event& EventCollection::getLongestEvent() const
{
	size_t idx = 0, max=events[0].getEnd().getDifference(events[0].getStart()).getTotalSeconds();

	for (size_t i = 1; i < count; i++) {
		size_t curr= events[i].getEnd().getDifference(events[i].getStart()).getTotalSeconds();

		if (curr>max) {
			curr = max;
			idx = i;
		}
	}

	return events[idx];
}

size_t EventCollection::maxCountOfEvents(const Date& date) const
{
	size_t c = 0;

	for (size_t i = 0; i < count; i++) {
		if (date.isEqualTo(events[i].getDate()) == 0) {
			c++;
		}
	}

	return c;
}

void EventCollection::removeEvent(const char* name)
{
	int idx = findEventByName(name);

	if (idx == -1)
		return;

	events[idx] = events[--count];
}

void EventCollection::print() const
{
	for (size_t i = 0; i < count; i++) {
		std::cout << events[i].getName() << std::endl;
	}
}

EventCollection EventCollection::eventsOnDate(const Date& date) const
{
	EventCollection res;

	for (size_t i = 0; i < count; i++) {
		if (date.isEqualTo(events[i].getDate()) == 0) {
			res.addEvent(events[i]);
		}
	}

	return res;
}

int EventCollection::findEventByName(const char* name) const
{
	for (size_t i = 0; i < count; i++) {
		if (strcmp(events[i].getName(), name) == 0) {
			return i;
		}
	}

	return -1;
}
