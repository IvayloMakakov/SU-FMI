#include "Event.h"
#pragma warning(disable : 4996)

Event::Event() : Event("Unknown", 1, 1, 1, 0, 0, 0, 0, 0, 0)
{
}

Event::Event(const char* name, const Date& date, const Time& s, const Time& e) : date(date), start(s), end(e)
{
	setName(name);
	
	validateTimes();
}

Event::Event(const char* name, unsigned day, unsigned month, unsigned year, unsigned startTimeHours, unsigned startTimeMins, unsigned startTimeSecs, unsigned endTimeHours, unsigned endTimeMins, unsigned endTimeSecs) : date(day, month, year),
start(startTimeHours, startTimeMins, startTimeSecs),
end(endTimeHours, endTimeMins, endTimeSecs)
{
	setName(name);

	validateTimes();
}

const char* Event::getName() const
{
	return name;
}

const Date& Event::getDate() const
{
	return date;
}

const Time& Event::getStart() const
{
	return start;
}

const Time& Event::getEnd() const
{
	return end;
}

void Event::setName(const char* name)
{
	if (!name||!*name)
	{
		return;
	}

	strcpy(this->name, name);
}

void Event::setDate(const Date& date)
{
	this->date = date;
}

void Event::setStartTime(const Time& startTime)
{
	if (startTime.compare(end) >= 1) {
		return;
	}

	this->start = startTime;
}

void Event::setEndTime(const Time& endTime)
{
	if (this->start.compare(endTime) >= 1) {
		return;
	}

	this->end = endTime;
}

void Event::validateTimes()
{
	if (start.compare(end) >= 1) {
		std::swap(start, end);
	}
}