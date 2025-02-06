package com.example.retrofitpoc.ui.mocks

import com.example.retrofitpoc.model.Articles
import com.example.retrofitpoc.model.Media
import com.example.retrofitpoc.model.MediaMetaData

val articlesMock: Articles = Articles(
    uri = "nyt://article/c350834f-8018-5e4e-a6f5-fd19274ac1b9",
    url = "https://www.nytimes.com/2025/02/05/magazine/sex-gen-x-women.html",
    id = 100000009952243,
    asset_id = 100000009952243,
    source = "New York Times",
    published_date = "2025-02-05",
    updated = "2025-02-05",
    section = "Magazine",
    subsection = "",
    nytdscetion = "magazine",
    axd_keyword =  "Women and Girls;Sex;Generation X;Middle Age (Demographic)",
    column = "",
    byline = "By Mireille Silcoff",
    type = "Article",
    title = "Why Gen X Women Are Having the Best Sex",
    abstract = "In an era plagued by sex negativity, only one generation seems immune: mine.",
    des_facet = listOf(
        "Women and Girls",
        "Sex",
        "Generation X",
        "Middle Age (Demographic)"
    ),
    org_facet = listOf(),
    per_facet = listOf(),
    geo_facet = listOf(),
    media = listOf(mediaMock),
    eta_id = 0
)

val mediaMock: Media
    get() = Media(
              type = "image",
              subtype = "photo",
              caption =  "",
              copyright =  "Naila Ruechel for The New York Times",
              approved_for_syndication =  1,
              mediametadata =  listOf<MediaMetaData>(mediaMetaDataMock, mediaMetaDataMock, mediaMetaDataMock)
    )

val mediaMetaDataMock : MediaMetaData = MediaMetaData(
    url = "https://static01.nyt.com/images/2025/02/09/magazine/09mag-genx/09mag-genx-thumbStandard.jpg",
    format =  "Standard Thumbnail",
    height =  75,
    width =  75
)