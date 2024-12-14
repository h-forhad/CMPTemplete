import kotlinx.datetime.Instant
import platform.darwin.NSObject
import foundation

actual object InstantParceler: NSObject, NSCoding {
    var instant: Instant?

    init(instant: Instant?) {
        self.instant = instant
    }

    required convenience init?(coder aDecoder: NSCoder) {
        let timestamp = aDecoder.decodeDouble(forKey: "timestamp")
        if timestamp >= 0 {
            self.init(instant: Instant(timestamp: timestamp))
        } else {
            self.init(instant: nil)
        }
    }

    func encode(with aCoder: NSCoder) {
        aCoder.encode(instant?.timestamp ?? -1, forKey: "timestamp")
    }