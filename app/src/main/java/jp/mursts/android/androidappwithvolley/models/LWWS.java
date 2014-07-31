package jp.mursts.android.androidappwithvolley.models;

import java.util.List;

public class LWWS {
    private static final String TAG = LWWS.class.getSimpleName();

    private String link;
    private List<Forecasts> forecasts;
    private String publicTime;
    private Location location;
    private String title;
    private Description description;

    public static String getTag() {
        return TAG;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public List<Forecasts> getForecasts() {
        return forecasts;
    }

    public void setForecasts(List<Forecasts> forecasts) {
        this.forecasts = forecasts;
    }

    public String getPublicTime() {
        return publicTime;
    }

    public void setPublicTime(String publicTime) {
        this.publicTime = publicTime;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Description getDescription() {
        return description;
    }

    public void setDescription(Description description) {
        this.description = description;
    }

    public class Forecasts {
        private String dateLabel;
        private String telop;
        private String date;
        private Temperature temperature;
        private Image image;

        @Override
        public String toString() {
            return dateLabel + "(" + date + ") : " + telop;
        }

        public String getDatelabel() {
            return dateLabel;
        }

        public void setDatelabel(String datelabel) {
            this.dateLabel = datelabel;
        }

        public String getTelop() {
            return telop;
        }

        public void setTelop(String telop) {
            this.telop = telop;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public Temperature getTemperature() {
            return temperature;
        }

        public void setTemperature(Temperature temperature) {
            this.temperature = temperature;
        }

        public Image getImage() {
            return image;
        }

        public void setImage(Image image) {
            this.image = image;
        }

        private class Temperature {
            private Min min;
            private Max max;

            public Min getMin() {
                return min;
            }

            public void setMin(Min min) {
                this.min = min;
            }

            public Max getMax() {
                return max;
            }

            public void setMax(Max max) {
                this.max = max;
            }

            private class Min {
                private String celsius;
                private String fahrenheit;

                public String getCelsius() {
                    return celsius;
                }

                public void setCelsius(String celsius) {
                    this.celsius = celsius;
                }

                public String getFahrenheit() {
                    return fahrenheit;
                }

                public void setFahrenheit(String fahrenheit) {
                    this.fahrenheit = fahrenheit;
                }
            }

            private class Max {
                public String getCelsius() {
                    return celsius;
                }

                public void setCelsius(String celsius) {
                    this.celsius = celsius;
                }

                public String getFahrenheit() {
                    return fahrenheit;
                }

                public void setFahrenheit(String fahrenheit) {
                    this.fahrenheit = fahrenheit;
                }

                private String celsius;
                private String fahrenheit;
            }

        }

        private class Image {
            public String getWidth() {
                return width;
            }

            public void setWidth(String width) {
                this.width = width;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getHeight() {
                return height;
            }

            public void setHeight(String height) {
                this.height = height;
            }

            private String width;
            private String url;
            private String title;
            private String height;
        }

    }

    public class Location {
        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getArea() {
            return area;
        }

        public void setArea(String area) {
            this.area = area;
        }

        public String getPrefecture() {
            return prefecture;
        }

        public void setPrefecture(String prefecture) {
            this.prefecture = prefecture;
        }

        private String city;
        private String area;
        private String prefecture;
    }

    public class Description {
        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public String getPublicTime() {
            return publicTime;
        }

        public void setPublicTime(String publicTime) {
            this.publicTime = publicTime;
        }

        private String text;
        private String publicTime;
    }
}
