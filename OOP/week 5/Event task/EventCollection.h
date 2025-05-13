#pragma once
#include "Event.h"
class EventCollection
{
public:
	void addEvent(const Event& event);

	const Event& getLongestEvent() const;

	size_t maxCountOfEvents(const Date& date) const;

	void removeEvent(const char* name);

	void print() const;

private:
	Event events[20];
	size_t count = 0;

	EventCollection eventsOnDate(const Date& date) const;
	int findEventByName(const char* name) const;
};

