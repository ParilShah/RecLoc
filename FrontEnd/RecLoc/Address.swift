//
//  Address.swift
//  RecLoc
//
//  Created by Paril Shah on 5/4/17.
//  Copyright © 2017 Paril Shah. All rights reserved.
//

import Foundation
import SwiftyJSON

struct Address {
    var addressLine1:String?
    var addressLine2:String?
    var city:String?
    var state:String?
    var country:String?
    var zip:Int?
    var latitude:Double?
    var longitude:Double?
    
    init(jsonObject:JSON) {
        addressLine1 = jsonObject["addressLine1"].string
        addressLine2 = jsonObject["addressLine2"].string
        city = jsonObject["city"].string
        state = jsonObject["state"].string
        country = jsonObject["country"].string
        zip = jsonObject["zip"].int
        latitude = jsonObject["latitude"].double
        longitude = jsonObject["longitude"].double
    }

}
