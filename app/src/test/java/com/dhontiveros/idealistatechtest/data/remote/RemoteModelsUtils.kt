package com.dhontiveros.idealistatechtest.data.remote

import com.dhontiveros.idealistatechtest.data.remote.model.EmissionsDto
import com.dhontiveros.idealistatechtest.data.remote.model.EnergyCertificationDto
import com.dhontiveros.idealistatechtest.data.remote.model.EnergyConsumptionDto
import com.dhontiveros.idealistatechtest.data.remote.model.FeaturesDto
import com.dhontiveros.idealistatechtest.data.remote.model.ImageDto
import com.dhontiveros.idealistatechtest.data.remote.model.ImagesDetailDto
import com.dhontiveros.idealistatechtest.data.remote.model.MoreCharacteristicsDto
import com.dhontiveros.idealistatechtest.data.remote.model.MultimediaDetailDto
import com.dhontiveros.idealistatechtest.data.remote.model.MultimediaDto
import com.dhontiveros.idealistatechtest.data.remote.model.PriceDto
import com.dhontiveros.idealistatechtest.data.remote.model.PriceInfoDetailDto
import com.dhontiveros.idealistatechtest.data.remote.model.PriceInfoDto
import com.dhontiveros.idealistatechtest.data.remote.model.PropertyDetailDto
import com.dhontiveros.idealistatechtest.data.remote.model.PropertyListItemDto
import com.dhontiveros.idealistatechtest.data.remote.model.UbicationDto

fun getMockPropertyListItemDto(propertyCode: String = "1") = PropertyListItemDto(
    propertyCode = propertyCode,
    thumbnail = "https://img4.idealista.com/blur/WEB_LISTING-M/0/id.pro.es.image.master/58/60/32/1273036727.webp",
    floor = "2",
    price = 1195000.0,
    priceInfo = PriceInfoDto(
        price = PriceDto(
            amount = 1195000.0,
            currencySuffix = "€"
        )
    ),
    propertyType = "flat",
    operation = "sale",
    size = 133.0,
    exterior = false,
    rooms = 3,
    bathrooms = 2,
    address = "calle de Lagasca",
    province = "Madrid",
    municipality = "Madrid",
    district = "Barrio de Salamanca",
    country = "es",
    neighborhood = "Castellana",
    latitude = 40.4362687,
    longitude = -3.6833686,
    description = "Venta.Piso EN EXCLUSIVA. Castellana. Se ofrece en venta vivienda de 133 m² en el exclusivo Barrio de Salamanca, zona Castellana, con 3 dormitorios (uno en suite), 2 baños, amplio salón comedor, cocina independiente con office y lavadero. Cuenta con un amplio patio privado y armarios empotrados en todas las habitaciones. Reformado para optimizar el espacio, ofrece gran potencial para personalizarlo. Ubicado en una de las zonas más exclusivas de Madrid, el Barrio de Salamanca, en la cotizada zona de Castellana, se encuentra este espacioso piso en venta de 133 m² que ofrece una oportunidad única para quienes buscan una vivienda que combine comodidad, ubicación y potencial de actualización. El inmueble cuenta con un salón -comedor, amplio y luminoso, es perfecto para disfrutar de momentos de convivencia con la familia o amigos, y está conectado a un patio de uso privativo, una verdadera joya en pleno centro de la ciudad. Este espacio exterior es ideal para relajarse, organizar comidas al aire libre o incluso para ser transformado en una zona verde que aporte tranquilidad y frescura al hogar. La cocina es independiente y dispone de una cómoda zona de office, perfecta para las comidas cotidianas, además de contar con un práctico lavadero adjunto que facilita las tareas del hogar. Además, dispone de tres amplios dormitorios, destacando el dormitorio principal, que es una suite completa con un baño privado, ofreciendo privacidad y confort. Los otros dos dormitorios también disponen de armarios empotrados, maximizando el almacenamiento en cada habitación.El piso incluye dos cuartos de baño completos, con un diseño moderno y funcional, ideales para el día a día de una familia o para invitados. Cabe destacar que este piso ha sido previamente reformado con una distribución que aprovecha al máximo el espacio disponible, brindando una excelente base para que puedas personalizarlo según tus gustos y necesidades.  MUCHAS DE NUESTRAS PROPIEDADES NO APARECEN EN IDEALISTA, CONSULTA NUESTRA PÁGINA WEB PARA VER MÁS PROPIEDADES EXCLUSIVAS. Sin duda, el distrito selecto por excelencia de Madrid. Pasear por las calles del barrio de Salamanca es contemplar los edificios más elegantes y codiciados de la capital: construcciones históricas de portales señoriales e interiores amplios y altos. Concebido en las últimas décadas del siglo XIX por D. José de Salamanca y Mayol, Marqués de Salamanca, este ensanche de la ciudad conserva intacto su aire aristocrático, que podemos ver en palacetes como el de la Embajada de Italia, por ejemplo. En este epicentro del lujo se localiza la conocida “Milla de Oro”: un área comercial donde tienen su sede marcas prestigiosas como Dior, Chanel, Prada o Tiffany y Promora. Tiendas como Gallery, BD o Vitra y restaurantes como Ramsés o No, responden a los gustos de un life style moderno y sofisticado. Hablamos de una zona dinámica y segura, de grandes aceras, con un parking casi ilimitado y con una interesante vida nocturna: Nxt Club, Déjate Besar o Le Boutique Club. No faltan las galerías de arte y los espacios dedicados a la cultura como la Fundación March o el museo Lázaro Galdiano. Sin olvidar sus árboles frondosos y la cercanía al parque del Retiro, el gran pulmón verde del centro de Madrid.",
    multimedia = MultimediaDto(
        images = listOf(
            ImageDto(
                url = "https://img4.idealista.com/blur/WEB_LISTING-M/0/id.pro.es.image.master/58/60/32/1273036727.webp",
                tag = "livingRoom"
            ),
            ImageDto(
                url = "https://img4.idealista.com/blur/WEB_LISTING-M/0/id.pro.es.image.master/a1/0f/ee/1273036728.webp",
                tag = "unknown"
            )
        )
    ),
    features = FeaturesDto(
        hasAirConditioning = true,
        hasBoxRoom = false
    )
)

fun getMockPropertyDetailItemDto(adid: Long = 1L) = PropertyDetailDto(
    adid = adid,
    price = 1195000.0,
    priceInfoDetail = PriceInfoDetailDto(
        amount = 1195000.0,
        currencySuffix = "€"
    ),
    operation = "sale",
    propertyType = "homes",
    extendedPropertyType = "flat",
    homeType = "flat",
    state = "active",
    multimedia = MultimediaDetailDto(
        images = listOf(
            ImagesDetailDto(
                url = "https://img4.idealista.com/blur/WEB_DETAIL-L-L/0/id.pro.es.image.master/58/60/32/1273036727.webp",
                tag = "livingRoom",
                localizedName = "Salón",
                multimediaId = 1273036727
            ),
            ImagesDetailDto(
                url = "https://img4.idealista.com/blur/WEB_DETAIL-L-L/0/id.pro.es.image.master/a1/0f/ee/1273036728.webp",
                tag = "unknown",
                localizedName = "Desconocido",
                multimediaId = 1273036728
            )
        )
    ),
    propertyComment = "Venta.Piso EN EXCLUSIVA. Castellana.",
    ubication = UbicationDto(
        latitude = 40.4362687,
        longitude = -3.6833686
    ),
    country = "es",
    moreCharacteristics = MoreCharacteristicsDto(
        communityCosts = 330.0,
        roomNumber = 3,
        bathNumber = 2,
        exterior = false,
        housingFurnitures = "unknown",
        agencyIsABank = false,
        energyCertificationType = "e",
        flatLocation = "internal",
        modificationDate = 1727683968000L,
        constructedArea = 133L,
        lift = true,
        boxroom = false,
        isDuplex = false,
        floor = "2",
        status = "renew"
    ),
    energyCertification = EnergyCertificationDto(
        title = "Certificado energético",
        energyConsumption = EnergyConsumptionDto(
            type = "e"
        ),
        emissions = EmissionsDto(
            type = "e"
        )
    )
)
