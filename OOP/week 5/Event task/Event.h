#pragma once
#include "Date.h"
#include "Time.h"

class Event
{
public:
	Event();

	Event(const char* name, const Date& date, const Time& s, const Time& e);

	Event(const char* name, unsigned day, unsigned month, unsigned year,
		unsigned startTimeHours, unsigned startTimeMins, unsigned startTimeSecs,
		unsigned endTimeHours, unsigned endTimeMins, unsigned endTimeSecs);

	const char* getName() const;
	const Date& getDate() const;
	const Time& getStart() const;
	const Time& getEnd() const;

	void setName(const char* name);
	void setDate(const Date& date);
	void setStartTime(const Time& startTime);
	void setEndTime(const Time& endTime);
private:
	char name[20 + 1]="";
	Date date;
	Time start;
	Time end;

	void validateTimes();
};