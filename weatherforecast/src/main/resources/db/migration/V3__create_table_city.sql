create table city(
id UUID primary key,
city_open_weather_id numeric not null unique,
foreign key(city_open_weather_id) references city_api_openweather(id)
);