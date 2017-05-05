//
//  Location.swift
//  RecLoc
//
//  Created by Paril Shah on 5/4/17.
//  Copyright © 2017 Paril Shah. All rights reserved.
//

import Foundation
import SwiftyJSON

struct Location {
    var locationName:String?
    var locationDescription:String?
    var tag:[JSON]?
    var address:Address?
    
    init(jsonObject:JSON) {
        locationName = jsonObject["locationName"].string
        locationDescription = jsonObject["locationDescription"].string
        tag = jsonObject["tags"].arrayValue
        address = Address.init(jsonObject: jsonObject["Address"])
    }
}
