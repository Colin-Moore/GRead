package com.colin.GoodGirlReads.Models;

public class BookResponse {
    private Items[] items;

    private String totalItems;

    private String kind;

    public Items[] getItems ()
    {
        return items;
    }

    public void setItems (Items[] items)
    {
        this.items = items;
    }

    public String getTotalItems ()
    {
        return totalItems;
    }

    public void setTotalItems (String totalItems)
    {
        this.totalItems = totalItems;
    }

    public String getKind ()
    {
        return kind;
    }

    public void setKind (String kind)
    {
        this.kind = kind;
    }


    public class Items
    {
        private SaleInfo saleInfo;

        private String id;

        private SearchInfo searchInfo;

        private String etag;

        private VolumeInfo volumeInfo;

        private String selfLink;

        private AccessInfo accessInfo;

        private String kind;

        public SaleInfo getSaleInfo ()
        {
            return saleInfo;
        }

        public void setSaleInfo (SaleInfo saleInfo)
        {
            this.saleInfo = saleInfo;
        }

        public String getId ()
        {
            return id;
        }

        public void setId (String id)
        {
            this.id = id;
        }

        public SearchInfo getSearchInfo ()
        {
            return searchInfo;
        }

        public void setSearchInfo (SearchInfo searchInfo)
        {
            this.searchInfo = searchInfo;
        }

        public String getEtag ()
        {
            return etag;
        }

        public void setEtag (String etag)
        {
            this.etag = etag;
        }

        public VolumeInfo getVolumeInfo ()
        {
            return volumeInfo;
        }

        public void setVolumeInfo (VolumeInfo volumeInfo)
        {
            this.volumeInfo = volumeInfo;
        }

        public String getSelfLink ()
        {
            return selfLink;
        }

        public void setSelfLink (String selfLink)
        {
            this.selfLink = selfLink;
        }

        public AccessInfo getAccessInfo ()
        {
            return accessInfo;
        }

        public void setAccessInfo (AccessInfo accessInfo)
        {
            this.accessInfo = accessInfo;
        }

        public String getKind ()
        {
            return kind;
        }

        public void setKind (String kind)
        {
            this.kind = kind;
        }

        public class SearchInfo
        {
            private String textSnippet;

            public String getTextSnippet ()
            {
                return textSnippet;
            }

            public void setTextSnippet (String textSnippet)
            {
                this.textSnippet = textSnippet;
            }
        }

        public class AccessInfo
        {
            private String webReaderLink;

            private String textToSpeechPermission;

            private String publicDomain;

            private String viewability;

            private String accessViewStatus;

            private Pdf pdf;

            private Epub epub;

            private String embeddable;

            private String quoteSharingAllowed;

            private String country;

            public String getWebReaderLink ()
            {
                return webReaderLink;
            }

            public void setWebReaderLink (String webReaderLink)
            {
                this.webReaderLink = webReaderLink;
            }

            public String getTextToSpeechPermission ()
            {
                return textToSpeechPermission;
            }

            public void setTextToSpeechPermission (String textToSpeechPermission)
            {
                this.textToSpeechPermission = textToSpeechPermission;
            }

            public String getPublicDomain ()
            {
                return publicDomain;
            }

            public void setPublicDomain (String publicDomain)
            {
                this.publicDomain = publicDomain;
            }

            public String getViewability ()
            {
                return viewability;
            }

            public void setViewability (String viewability)
            {
                this.viewability = viewability;
            }

            public String getAccessViewStatus ()
            {
                return accessViewStatus;
            }

            public void setAccessViewStatus (String accessViewStatus)
            {
                this.accessViewStatus = accessViewStatus;
            }

            public Pdf getPdf ()
            {
                return pdf;
            }

            public void setPdf (Pdf pdf)
            {
                this.pdf = pdf;
            }

            public Epub getEpub ()
            {
                return epub;
            }

            public void setEpub (Epub epub)
            {
                this.epub = epub;
            }

            public String getEmbeddable ()
            {
                return embeddable;
            }

            public void setEmbeddable (String embeddable)
            {
                this.embeddable = embeddable;
            }

            public String getQuoteSharingAllowed ()
            {
                return quoteSharingAllowed;
            }

            public void setQuoteSharingAllowed (String quoteSharingAllowed)
            {
                this.quoteSharingAllowed = quoteSharingAllowed;
            }

            public String getCountry ()
            {
                return country;
            }

            public void setCountry (String country)
            {
                this.country = country;
            }

            public class Pdf
            {
                private String acsTokenLink;

                private String isAvailable;

                public String getAcsTokenLink ()
                {
                    return acsTokenLink;
                }

                public void setAcsTokenLink (String acsTokenLink)
                {
                    this.acsTokenLink = acsTokenLink;
                }

                public String getIsAvailable ()
                {
                    return isAvailable;
                }

                public void setIsAvailable (String isAvailable)
                {
                    this.isAvailable = isAvailable;
                }
            }

            public class Epub
            {
                private String acsTokenLink;

                private String isAvailable;

                public String getAcsTokenLink ()
                {
                    return acsTokenLink;
                }

                public void setAcsTokenLink (String acsTokenLink)
                {
                    this.acsTokenLink = acsTokenLink;
                }

                public String getIsAvailable ()
                {
                    return isAvailable;
                }

                public void setIsAvailable (String isAvailable)
                {
                    this.isAvailable = isAvailable;
                }
            }
        }

        public class SaleInfo
        {
            private RetailPrice retailPrice;

            private String saleability;

            private ListPrice listPrice;

            private Offers[] offers;

            private String buyLink;

            private String isEbook;

            private String country;

            public RetailPrice getRetailPrice ()
            {
                return retailPrice;
            }

            public void setRetailPrice (RetailPrice retailPrice)
            {
                this.retailPrice = retailPrice;
            }

            public String getSaleability ()
            {
                return saleability;
            }

            public void setSaleability (String saleability)
            {
                this.saleability = saleability;
            }

            public ListPrice getListPrice ()
            {
                return listPrice;
            }

            public void setListPrice (ListPrice listPrice)
            {
                this.listPrice = listPrice;
            }

            public Offers[] getOffers ()
            {
                return offers;
            }

            public void setOffers (Offers[] offers)
            {
                this.offers = offers;
            }

            public String getBuyLink ()
            {
                return buyLink;
            }

            public void setBuyLink (String buyLink)
            {
                this.buyLink = buyLink;
            }

            public String getIsEbook ()
            {
                return isEbook;
            }

            public void setIsEbook (String isEbook)
            {
                this.isEbook = isEbook;
            }

            public String getCountry ()
            {
                return country;
            }

            public void setCountry (String country)
            {
                this.country = country;
            }

            public class Offers
            {
                private RetailPrice retailPrice;

                private ListPrice listPrice;

                private String finskyOfferType;

                public RetailPrice getRetailPrice ()
                {
                    return retailPrice;
                }

                public void setRetailPrice (RetailPrice retailPrice)
                {
                    this.retailPrice = retailPrice;
                }

                public ListPrice getListPrice ()
                {
                    return listPrice;
                }

                public void setListPrice (ListPrice listPrice)
                {
                    this.listPrice = listPrice;
                }

                public String getFinskyOfferType ()
                {
                    return finskyOfferType;
                }

                public void setFinskyOfferType (String finskyOfferType)
                {
                    this.finskyOfferType = finskyOfferType;
                }
            }

            public class RetailPrice
            {
                private String amount;

                private String currencyCode;

                public String getAmount ()
                {
                    return amount;
                }

                public void setAmount (String amount)
                {
                    this.amount = amount;
                }

                public String getCurrencyCode ()
                {
                    return currencyCode;
                }

                public void setCurrencyCode (String currencyCode)
                {
                    this.currencyCode = currencyCode;
                }
            }

            public class ListPrice
            {
                private String amount;

                private String currencyCode;

                public String getAmount ()
                {
                    return amount;
                }

                public void setAmount (String amount)
                {
                    this.amount = amount;
                }

                public String getCurrencyCode ()
                {
                    return currencyCode;
                }

                public void setCurrencyCode (String currencyCode)
                {
                    this.currencyCode = currencyCode;
                }
            }
        }

        public class VolumeInfo
        {
            private String pageCount;

            private String averageRating;

            private ReadingModes readingModes;

            private String infoLink;

            private String printType;

            private String allowAnonLogging;

            private String publisher;

            private String[] authors;

            private String canonicalVolumeLink;

            private String title;

            private String previewLink;

            private String description;

            private String ratingsCount;

            private ImageLinks imageLinks;

            private String contentVersion;

            private String[] categories;

            private String language;

            private String publishedDate;

            private IndustryIdentifiers[] industryIdentifiers;

            private String maturityRating;

            public String getPageCount ()
            {
                return pageCount;
            }

            public void setPageCount (String pageCount)
            {
                this.pageCount = pageCount;
            }

            public String getAverageRating ()
            {
                return averageRating;
            }

            public void setAverageRating (String averageRating)
            {
                this.averageRating = averageRating;
            }

            public ReadingModes getReadingModes ()
            {
                return readingModes;
            }

            public void setReadingModes (ReadingModes readingModes)
            {
                this.readingModes = readingModes;
            }

            public String getInfoLink ()
            {
                return infoLink;
            }

            public void setInfoLink (String infoLink)
            {
                this.infoLink = infoLink;
            }

            public String getPrintType ()
            {
                return printType;
            }

            public void setPrintType (String printType)
            {
                this.printType = printType;
            }

            public String getAllowAnonLogging ()
            {
                return allowAnonLogging;
            }

            public void setAllowAnonLogging (String allowAnonLogging)
            {
                this.allowAnonLogging = allowAnonLogging;
            }

            public String getPublisher ()
            {
                return publisher;
            }

            public void setPublisher (String publisher)
            {
                this.publisher = publisher;
            }

            public String[] getAuthors ()
            {
                return authors;
            }

            public void setAuthors (String[] authors)
            {
                this.authors = authors;
            }

            public String getCanonicalVolumeLink ()
            {
                return canonicalVolumeLink;
            }

            public void setCanonicalVolumeLink (String canonicalVolumeLink)
            {
                this.canonicalVolumeLink = canonicalVolumeLink;
            }

            public String getTitle ()
            {
                return title;
            }

            public void setTitle (String title)
            {
                this.title = title;
            }

            public String getPreviewLink ()
            {
                return previewLink;
            }

            public void setPreviewLink (String previewLink)
            {
                this.previewLink = previewLink;
            }

            public String getDescription ()
            {
                return description;
            }

            public void setDescription (String description)
            {
                this.description = description;
            }

            public String getRatingsCount ()
            {
                return ratingsCount;
            }

            public void setRatingsCount (String ratingsCount)
            {
                this.ratingsCount = ratingsCount;
            }

            public ImageLinks getImageLinks ()
            {
                return imageLinks;
            }

            public void setImageLinks (ImageLinks imageLinks)
            {
                this.imageLinks = imageLinks;
            }

            public String getContentVersion ()
            {
                return contentVersion;
            }

            public void setContentVersion (String contentVersion)
            {
                this.contentVersion = contentVersion;
            }

            public String[] getCategories ()
            {
                return categories;
            }

            public void setCategories (String[] categories)
            {
                this.categories = categories;
            }

            public String getLanguage ()
            {
                return language;
            }

            public void setLanguage (String language)
            {
                this.language = language;
            }

            public String getPublishedDate ()
            {
                return publishedDate;
            }

            public void setPublishedDate (String publishedDate)
            {
                this.publishedDate = publishedDate;
            }

            public IndustryIdentifiers[] getIndustryIdentifiers ()
            {
                return industryIdentifiers;
            }

            public void setIndustryIdentifiers (IndustryIdentifiers[] industryIdentifiers)
            {
                this.industryIdentifiers = industryIdentifiers;
            }

            public String getMaturityRating ()
            {
                return maturityRating;
            }

            public void setMaturityRating (String maturityRating)
            {
                this.maturityRating = maturityRating;
            }

            public class ImageLinks
            {
                private String thumbnail;

                private String smallThumbnail;

                public String getThumbnail ()
                {
                    return thumbnail;
                }

                public void setThumbnail (String thumbnail)
                {
                    this.thumbnail = thumbnail;
                }

                public String getSmallThumbnail ()
                {
                    return smallThumbnail;
                }

                public void setSmallThumbnail (String smallThumbnail)
                {
                    this.smallThumbnail = smallThumbnail;
                }
            }

            public class ReadingModes
            {
                private String text;

                private String image;

                public String getText ()
                {
                    return text;
                }

                public void setText (String text)
                {
                    this.text = text;
                }

                public String getImage ()
                {
                    return image;
                }

                public void setImage (String image)
                {
                    this.image = image;
                }
            }

            public class IndustryIdentifiers
            {
                private String type;

                private String identifier;

                public String getType ()
                {
                    return type;
                }

                public void setType (String type)
                {
                    this.type = type;
                }

                public String getIdentifier ()
                {
                    return identifier;
                }

                public void setIdentifier (String identifier)
                {
                    this.identifier = identifier;
                }
            }
        }
    }
}
